package com.example.sasukeapp.data.pagingSource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.sasukeapp.data.local.SasukeDatabase
import com.example.sasukeapp.data.remote.SasukeApi
import com.example.sasukeapp.domain.model.Hero
import com.example.sasukeapp.domain.model.HeroRemoteKeys

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator(
    private val sasukeApi: SasukeApi,
    private val sasukeDatabase: SasukeDatabase
) : RemoteMediator<Int, Hero>() {


    private val heroDao = sasukeDatabase.heroDao()
    private val heroRemoteKeyDao = sasukeDatabase.heroRemoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeyDao.getRemoteKey(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
      return try {
            // Finding the correct page number
            val page = when (loadType) {
                // When app is running for the first time
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val res = sasukeApi.getAllHeroes(page = page)
            if (res.heroes.isNotEmpty()) {
                // saving all the queries in the database sequentially
                sasukeDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        heroRemoteKeyDao.deleteAllRemoteKeys()
                    }
                    val prevPage = res.prevPage
                    val nextPage = res.nextPage
                    val keys = res.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage ,
                            lastUpdated = res.lastUpdated
                        )
                    }
                    // saving keys and heroes to database
                    heroRemoteKeyDao.addAllRemoteKeys(keys)
                    heroDao.addHeroes(res.heroes)
                }
            }
                // when we have no page to load from the server
               MediatorResult.Success(endOfPaginationReached = res.nextPage == null)
        }
       catch (e: Exception) {
           return MediatorResult.Error(e)
        }
    }

    // On refresh
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeyDao.getRemoteKey(id = id)
            }
        }
    }

    // On first page
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                heroRemoteKeyDao.getRemoteKey(id = hero.id)
            }
    }

    // On Last page
    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                heroRemoteKeyDao.getRemoteKey(id = hero.id)
            }
    }
}