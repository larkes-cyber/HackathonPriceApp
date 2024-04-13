package com.larkes.hackathonpriceapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.larkes.hackathonpriceapp.android.screen.auth.AuthScreen
import com.larkes.hackathonpriceapp.android.screen.history.HistoryScreen
import com.larkes.hackathonpriceapp.android.screen.main.MainScreen
import com.larkes.hackathonpriceapp.android.screen.splash.SplashScreen
import com.larkes.hackathonpriceapp.android.viewmodels.auth.AuthViewModel
import com.larkes.hackathonpriceapp.android.viewmodels.splash.SplashViewModel

@Composable
fun Navigation(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){

        composable(Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(Screen.HistoryScreen.route){
            HistoryScreen(navController = navController)
        }

        composable(Screen.SplashScreen.route){
            val viewModel:SplashViewModel = hiltViewModel()
            SplashScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(Screen.AuthScreen.route){
            val viewModel:AuthViewModel = hiltViewModel()
            AuthScreen(navController = navController, viewModel = viewModel)
        }

    }

}