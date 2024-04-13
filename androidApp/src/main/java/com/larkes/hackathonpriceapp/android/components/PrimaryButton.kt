package com.larkes.hackathonpriceapp.android.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    title:String = "",
    enabled:Boolean = true,
    onClick:() -> Unit = {}
) {

    Button(
        enabled = enabled,
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.White
        )
    }


}