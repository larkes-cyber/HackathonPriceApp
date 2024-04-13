package com.larkes.hackathonpriceapp.android.viewmodels.auth.models

data class AuthState(
    val number:String? = null,
    val email:String? = null,
    val password:String = "",
    val isEmailMethod:Boolean = true,
    val isRegistration:Boolean = true,
    val passwordHidden:Boolean = true
)