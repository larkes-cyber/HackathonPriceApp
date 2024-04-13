package com.larkes.hackathonpriceapp.android.viewmodels.auth.models

sealed class AuthEvent{
    data object AuthSwitcherClicked:AuthEvent()
    data class EmailChanged(val email:String):AuthEvent()
    data class NumberChanged(val number:String):AuthEvent()
    data class PasswordChanged(val password:String):AuthEvent()
    data object SwitchLoginMethod:AuthEvent()
    data object PasswordHiddenChanged:AuthEvent()

}