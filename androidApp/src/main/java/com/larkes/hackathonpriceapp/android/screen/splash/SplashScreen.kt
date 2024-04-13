package com.larkes.hackathonpriceapp.android.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.larkes.hackathonpriceapp.android.navigation.Screen
import com.larkes.hackathonpriceapp.android.screen.splash.view.SplashView
import com.larkes.hackathonpriceapp.android.viewmodels.splash.SplashViewModel
import com.larkes.hackathonpriceapp.android.viewmodels.splash.models.SplashAction
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navController: NavController
) {

    viewModel.viewAction.collectAsState()

    LaunchedEffect(viewModel.viewAction){
        when(viewModel.viewAction.value){
            is SplashAction.OpenAuthScreen -> {
                navController.navigate(Screen.AuthScreen.route)
            }
            is SplashAction.OpenMainScreen -> {
                navController.navigate(Screen.MainScreen.route)
            }

            else -> {}
        }
    }
    

    SplashView()


}