package com.example.sasukeapp.presentation.screen.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.sasukeapp.presentation.common.AnimeListContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.json.JsonNull.content

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController ,
    searchViewModel: SearchViewModel = hiltViewModel()

) {

    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopAppBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        } ,
    content = {
        AnimeListContent(heroes = heroes, navController = navController)
    }
    )
}