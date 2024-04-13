package com.larkes.hackathonpriceapp.android.viewmodels.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larkes.hackathonpriceapp.android.viewmodels.splash.models.SplashAction
import com.larkes.hackathonpriceapp.di.InjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor():ViewModel() {

    private val _viewAction = MutableStateFlow<SplashAction>(SplashAction.OpenSplashScreen)
    val viewAction:StateFlow<SplashAction> = _viewAction

    init {
        checkAuth()
    }
    private fun checkAuth(){
        viewModelScope.launch {
            val res = InjectUseCase.useCheckAuth.execute()
            _viewAction.value = if(res.message == null) SplashAction.OpenMainScreen else SplashAction.OpenAuthScreen
        }
    }

}