package com.larkes.hackathonpriceapp.android.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password:String = "",
    hidden:Boolean = false,
    enabled:Boolean = true,
    onHidden:() -> Unit = {},
    onChange:(String) -> Unit = {}
) {

    val scope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    TextField(
        value = password,
        onValueChange = {
            onChange(it)
        },
        shape = RoundedCornerShape(10.dp),
        enabled = enabled,
        placeholder = {
            Text(
                text = "Пароль",
                color = Color.Gray.copy(alpha = 0.7f)
            )
        },
        textStyle = TextStyle(fontSize = 18.sp),
        visualTransformation =if(hidden) PasswordVisualTransformation()   else VisualTransformation.None,
        trailingIcon = {
            Icon(
                imageVector = if(hidden) Icons.Outlined.Clear  else Icons.Outlined.Lock,
                contentDescription = "",
                modifier = Modifier.clickable {
                    onHidden()
                },
                tint = Color.Gray.copy(alpha = 0.7f)
            )
        },
        modifier = modifier
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent {
                if (it.isFocused) {
                    scope.launch {
                        delay(200)
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
    )

}