package com.example.sasukeapp.domain.usecases

import com.example.sasukeapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    // Execute this function on background without calling it explicitly
    suspend operator fun invoke(completed : Boolean){
           repository.saveOnBoardingState(completed)
    }
}