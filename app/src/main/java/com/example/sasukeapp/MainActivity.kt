package com.example.sasukeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sasukeapp.navigation.SetupNavGraph
import com.example.sasukeapp.ui.theme.SasukeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint  // Where to inject dependencies that dagger provides
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SasukeAppTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
