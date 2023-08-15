package com.example.sasukeapp.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel  = hiltViewModel()
    ) {

    val allHeroes = homeViewModel.getAllHeroes.invoke().collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        },
        content = {
//            ListContent(
//                heroes = allHeroes,
//                navController = navController
//            )
        }
    )
}