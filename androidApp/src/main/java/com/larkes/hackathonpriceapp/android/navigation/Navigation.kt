package com.larkes.hackathonpriceapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.larkes.hackathonpriceapp.android.screen.history.HistoryScreen
import com.larkes.hackathonpriceapp.android.screen.main.MainScreen

@Composable
fun Navigation(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.MainScreen.route){

        composable(Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(Screen.HistoryScreen.route){
            HistoryScreen(navController = navController)
        }

    }

}