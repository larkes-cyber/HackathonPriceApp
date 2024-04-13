package com.larkes.hackathonpriceapp.android.viewmodels.splash.models

sealed class SplashAction {
    data object OpenSplashScreen:SplashAction()
    data object OpenMainScreen:SplashAction()
    data object OpenAuthScreen:SplashAction()
}