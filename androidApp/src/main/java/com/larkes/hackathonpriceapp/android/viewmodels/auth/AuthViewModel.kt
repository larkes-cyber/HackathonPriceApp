package com.larkes.hackathonpriceapp.android.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthAction
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthEvent
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthState
import com.larkes.hackathonpriceapp.di.InjectUseCase
import com.larkes.hackathonpriceapp.domain.model.AuthData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor():ViewModel() {

    private val _authAction = MutableStateFlow<AuthAction>(AuthAction.OpenAuthScreen)
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
            is AuthEvent.Done -> {
                obtainDone()

            }
            else -> {

            }

        }

    }


    private fun obtainDone() {
        viewModelScope.launch {

                _authUIState.value = authUIState.value.copy(isLoading = true, error = "")
                if(authUIState.value.isRegistration){
                   val res = InjectUseCase.useRegistrationUser.execute(AuthData(
                        email = authUIState.value.email,
                        number = authUIState.value.number,
                        password = authUIState.value.password
                    ))

                    if(res.data == null){
                        _authUIState.value = authUIState.value.copy(error = "Неверная почта или номер", isLoading = false)
                        return@launch
                    }

                }

            val res = InjectUseCase.useLoginUser.execute(
                AuthData(
                    email = authUIState.value.email,
                    number = authUIState.value.number,
                    password = authUIState.value.password
                )
            )
            if(res.data == null){
                _authUIState.value = authUIState.value.copy(error = "Неверная почта или номер", isLoading = false)
                return@launch
            }
            _authAction.value = AuthAction.OpenSplashScreen
            _authUIState.value = authUIState.value.copy(isLoading = false)


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
            email = null,
            number = null,
            password = ""
        )

    }

    private fun obtainPasswordChanged(password: String) {
        _authUIState.value = authUIState.value.copy(password = password)
    }




    private fun obtainAuthSwitcherClicked() {
        _authUIState.value = authUIState.value.copy(
            isRegistration = authUIState.value.isRegistration.not(),
            email = null,
            number = null,
            password = ""
        )
    }

}


