package com.larkes.hackathonpriceapp.android.screen.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.larkes.hackathonpriceapp.android.navigation.Screen
import com.larkes.hackathonpriceapp.android.screen.auth.views.AuthView
import com.larkes.hackathonpriceapp.android.viewmodels.auth.AuthViewModel
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthAction
import com.larkes.hackathonpriceapp.android.viewmodels.splash.SplashViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {
    val viewAction by viewModel.authAction.collectAsState()
    val viewState by viewModel.authUIState.collectAsState()

    LaunchedEffect(viewAction){
        when(viewAction){
            is AuthAction.OpenSplashScreen -> {
                navController.navigate(Screen.SplashScreen.route)
            }

            else -> {}
        }
    }

    AuthView(state = viewState){
        viewModel.onEvent(it)
    }



}