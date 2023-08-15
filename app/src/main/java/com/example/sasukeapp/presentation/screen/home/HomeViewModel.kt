package com.example.sasukeapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.sasukeapp.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  useCases: UseCases
)  : ViewModel() {

    val getAllHeroes = useCases.getAllHeroesUseCase
}
