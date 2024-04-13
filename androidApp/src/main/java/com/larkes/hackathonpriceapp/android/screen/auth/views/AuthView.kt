package com.larkes.hackathonpriceapp.android.screen.auth.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larkes.hackathonpriceapp.android.components.AuthPrimaryButton
import com.larkes.hackathonpriceapp.android.components.AuthTabButton
import com.larkes.hackathonpriceapp.android.components.AuthTextField
import com.larkes.hackathonpriceapp.android.components.PasswordTextField
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthEvent
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthState

@Composable
fun AuthView(
    state:AuthState,
    onEvent:(AuthEvent) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Text(
                text = if(state.isRegistration) "Регистрация" else "Вход",
                fontSize = 27.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(40.dp))
            if(state.isRegistration.not()){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AuthTabButton(
                        title = "Почта",
                        modifier = Modifier
                            .height(42.dp)
                            .weight(1f),
                        isActive = state.isEmailMethod,
                    ){
                        onEvent(AuthEvent.SwitchLoginMethod)
                    }
                    AuthTabButton(
                        title = "Номер",
                        modifier = Modifier
                            .height(42.dp)
                            .weight(1f),
                        isActive = state.isEmailMethod.not()
                    ){
                        onEvent(AuthEvent.SwitchLoginMethod)
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                if(state.isRegistration.not()){
                    AuthTextField(
                        title = if(state.isEmailMethod) "Введите почту" else "Введите номер",
                        text = if(state.isEmailMethod) state.email ?: "" else state.number ?: "",
                        modifier = Modifier.fillMaxWidth()
                    ){
                        onEvent(if(state.isEmailMethod) AuthEvent.EmailChanged(it) else AuthEvent.NumberChanged(it))
                    }
                }else{
                    AuthTextField(
                        title = "Введите почту",
                        text = state.email ?: "",
                        modifier = Modifier.fillMaxWidth()
                    ){
                        onEvent(AuthEvent.EmailChanged(it))
                    }
                    AuthTextField(
                        title = "Введите номер",
                        text = state.number ?: "",
                        modifier = Modifier.fillMaxWidth()
                    ){
                        onEvent(AuthEvent.NumberChanged(it))
                    }
                }
                PasswordTextField(
                    password = state.password,
                    onHidden = {
                        onEvent(AuthEvent.PasswordHiddenChanged)
                    },
                    hidden = state.passwordHidden,
                    modifier = Modifier.fillMaxWidth()
                ){
                    onEvent(AuthEvent.PasswordChanged(it))
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
            AuthPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                title = if(state.isRegistration.not()) "Войти" else "Зарегистрироваться",
                enabled = state.isLoading.not()
            ){
                onEvent(AuthEvent.Done)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if(state.isRegistration) "Войти" else "Зарегистрироваться",
                    color = Color.Blue,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        onEvent(AuthEvent.AuthSwitcherClicked)
                    },
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = state.error,
                    fontSize = 16.sp,
                    color = Color.Red
                )
            }
        }

    }

}