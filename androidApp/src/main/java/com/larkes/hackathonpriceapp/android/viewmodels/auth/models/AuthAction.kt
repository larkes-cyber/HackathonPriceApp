package com.larkes.hackathonpriceapp.android.viewmodels.auth.models

import com.larkes.hackathonpriceapp.android.viewmodels.splash.models.SplashAction

sealed class AuthAction {
    data object OpenSplashScreen:AuthAction()
    data object OpenAuthScreen:AuthAction()
}