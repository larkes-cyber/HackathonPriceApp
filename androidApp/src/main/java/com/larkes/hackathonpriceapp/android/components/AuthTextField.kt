package com.larkes.hackathonpriceapp.android.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun AuthTextField(
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
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Text(
                text = title,
                color = Color.Gray.copy(alpha = 0.7f)
            )

        }
    )


}