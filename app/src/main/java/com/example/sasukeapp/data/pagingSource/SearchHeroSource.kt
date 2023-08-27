package com.example.sasukeapp.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sasukeapp.data.remote.SasukeApi
import com.example.sasukeapp.domain.model.Hero
import java.lang.Exception
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val sasukeApi: SasukeApi,
    private val query: String
) : PagingSource<Int, Hero>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val apiResponse = sasukeApi.searchHeroes(name = query)
            val heroes = apiResponse.heroes
            if (heroes.isNotEmpty()) {
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }
}