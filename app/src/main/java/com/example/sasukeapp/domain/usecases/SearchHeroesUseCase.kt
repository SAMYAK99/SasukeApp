package com.example.sasukeapp.domain.usecases

import androidx.paging.PagingData
import com.example.sasukeapp.data.repository.Repository
import com.example.sasukeapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query = query)
    }
}