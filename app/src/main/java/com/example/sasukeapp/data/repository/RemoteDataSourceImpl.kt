package com.example.sasukeapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sasukeapp.data.local.SasukeDatabase
import com.example.sasukeapp.data.pagingSource.HeroRemoteMediator
import com.example.sasukeapp.data.remote.SasukeApi
import com.example.sasukeapp.domain.model.Hero
import com.example.sasukeapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
     val sasukeApi: SasukeApi ,
     val sasukeDatabase: SasukeDatabase
)  : RemoteDataSource{

    private val heroDao = sasukeDatabase.heroDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = HeroRemoteMediator(
                sasukeApi = sasukeApi ,
                sasukeDatabase = sasukeDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }

}