package com.larkes.hackathonpriceapp.android.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    enabled:Boolean = true,
    title:String = "",
    text:String = "",
    onChange:(String) -> Unit = {}
) {

    TextField(
        value = text,
        onValueChange = {value ->
            onChange(value)
        },
        modifier = modifier,
        enabled = enabled,
        keyboardActions = KeyboardActions(onDone = {}),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Text(
                text = title,
                color = Color.Gray.copy(alpha = 0.7f)
            )

        }
    )


}