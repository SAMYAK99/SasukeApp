package com.example.sasukeapp.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sasukeapp.domain.model.Hero
import com.example.sasukeapp.domain.usecases.UseCases
import com.example.sasukeapp.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedHero.value = heroId?.let { useCases.getSelectedHeroUseCase(heroId = heroId) }
        }
    }

}