package com.example.sasukeapp.presentation.screen.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun DetailScreen(
    navController : NavHostController ,
    detailsViewModel : DetailsViewModel = hiltViewModel()
) {

    val selectedHero by detailsViewModel.selectedHero.collectAsState()

    DetailsContent(
        navController = navController,
        selectedHero = selectedHero
    )
}