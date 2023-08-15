package com.example.sasukeapp.domain.usecases

data class UseCases(
    val readOnBoardingUseCase: ReadOnBoardingUseCase ,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase ,
    val getAllHeroesUseCase: GetAllHeroesUseCase
)
