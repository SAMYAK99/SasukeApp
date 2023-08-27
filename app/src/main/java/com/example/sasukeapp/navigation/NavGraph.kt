package com.example.sasukeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sasukeapp.presentation.screen.detail.DetailScreen
import com.example.sasukeapp.presentation.screen.home.HomeScreen
import com.example.sasukeapp.presentation.screen.onboarding.OnboardingScreen
import com.example.sasukeapp.presentation.screen.search.SearchScreen
import com.example.sasukeapp.presentation.screen.splash.SplashScreen
import com.example.sasukeapp.util.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
                 SplashScreen(navController)
        }
        composable(route = Screen.Welcome.route) {
                OnboardingScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
              HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
              DetailScreen(navController =navController)
        }
        composable(route = Screen.Search.route) {
             SearchScreen(navController = navController)
        }
    }
}