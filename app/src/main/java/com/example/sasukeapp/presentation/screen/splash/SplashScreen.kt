package com.example.sasukeapp.presentation.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sasukeapp.R
import com.example.sasukeapp.navigation.Screen
import com.example.sasukeapp.ui.theme.blue500
import com.example.sasukeapp.ui.theme.blue700

@Composable
fun SplashScreen(
    navController: NavController ,
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
) {
    val onBoardingCompleted by splashScreenViewModel.onBoardingCompleted.collectAsState()
    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        // splash screen rotating animation
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        // home screen routing
        navController.popBackStack()
        if(onBoardingCompleted){
            navController.navigate(Screen.Home.route)
        }
        else{
            navController.navigate(Screen.Welcome.route)
        }

    }
    Splash(degrees = degrees.value)
}


@Composable
fun Splash(degrees : Float) {
    if(isSystemInDarkTheme()){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(
                    R.string.app_logo
                ) ,
                modifier = Modifier.rotate(degrees)
            )
        }
    }
    else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(blue700, blue500)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo" ,
                modifier = Modifier.rotate(degrees)
            )
        }
    }
}


@Preview
@Composable
fun PreviewSplashScreen() {
    Splash(0f)
}