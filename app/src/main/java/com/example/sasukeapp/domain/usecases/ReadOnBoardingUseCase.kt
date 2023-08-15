package com.example.sasukeapp.domain.usecases

import com.example.sasukeapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke() : Flow<Boolean>{
        return repository.readOnBoardingState()
    }
}