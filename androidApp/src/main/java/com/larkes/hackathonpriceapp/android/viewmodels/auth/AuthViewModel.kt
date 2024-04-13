package com.larkes.hackathonpriceapp.android.viewmodels.auth

import androidx.lifecycle.ViewModel
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthAction
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthEvent
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor():ViewModel() {

    private val _authAction = MutableStateFlow(AuthAction.OpenAuthScreen)
    val authAction:StateFlow<AuthAction> = _authAction
    private val _authUIState = MutableStateFlow(AuthState())
    val authUIState:StateFlow<AuthState> = _authUIState

    fun onEvent(event:AuthEvent){

        when(event){
            is AuthEvent.AuthSwitcherClicked -> {
                obtainAuthSwitcherClicked()
            }

            is AuthEvent.PasswordChanged -> {
                obtainPasswordChanged(event.password)
            }
            is AuthEvent.SwitchLoginMethod -> {
                obtainSwitchLogin()
            }
            is AuthEvent.PasswordHiddenChanged -> {
                obtainPasswordHiddenChanged()
            }
            is AuthEvent.EmailChanged -> {
                obtainEmailChanged(event.email)
            }
            is AuthEvent.NumberChanged -> {
                obtainNumberChanged(event.number)
            }

        }

    }

    private fun obtainNumberChanged(number:String) {
        _authUIState.value = authUIState.value.copy(number = number)
    }

    private fun obtainEmailChanged(email:String) {
        _authUIState.value = authUIState.value.copy(email = email)
    }

    private fun obtainPasswordHiddenChanged() {
        _authUIState.value = authUIState.value.copy(passwordHidden = authUIState.value.passwordHidden.not())
    }

    private fun obtainSwitchLogin() {
        _authUIState.value = authUIState.value.copy(
            isEmailMethod = authUIState.value.isEmailMethod.not(),
            email = "",
            number = "",
            password = ""
        )

    }

    private fun obtainPasswordChanged(password: String) {
        _authUIState.value = authUIState.value.copy(password = password)
    }




    private fun obtainAuthSwitcherClicked() {
        _authUIState.value = authUIState.value.copy(
            isRegistration = authUIState.value.isRegistration.not(),
            number = "",
            email = "",
            password = ""
        )
    }

}


