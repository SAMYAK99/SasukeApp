package com.example.sasukeapp.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sasukeapp.navigation.Screen
import com.example.sasukeapp.presentation.common.AnimeListContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel  = hiltViewModel()
    ) {

    val allHeroes = homeViewModel.getAllHeroes.invoke().collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        },
        content = {
           AnimeListContent(
               navController = navController ,
               heroes = allHeroes
           )
        }
    )
}