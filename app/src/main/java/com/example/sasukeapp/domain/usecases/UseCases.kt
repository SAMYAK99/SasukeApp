package com.example.sasukeapp.domain.usecases


/*
* Use case will be directly interacting with the repository
* */

data class UseCases(
    val readOnBoardingUseCase: ReadOnBoardingUseCase ,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase ,
    val getAllHeroesUseCase: GetAllHeroesUseCase ,
    val searchHeroesUseCase: SearchHeroesUseCase ,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)
