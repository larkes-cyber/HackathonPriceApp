package com.larkes.hackathonpriceapp.android.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AuthTabButton(
    modifier: Modifier = Modifier,
    isActive:Boolean = false,
    title:String = "",
    enabled:Boolean = true,
    onClick:() -> Unit = {}
) {
    println(isActive.toString() +"  " + title + "  ggggggg")

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = if(isActive) Color.Blue else Color.Transparent),
        border = BorderStroke(width = 1.dp, color = Color.Blue),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled
    ){
        Text(
            text = title,
            color = if(isActive) Color.White else Color.Blue
        )
    }

}