package com.larkes.hackathonpriceapp.android.screen.main.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larkes.hackathonpriceapp.android.viewmodels.main.models.MainEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedSheet(
    sheetState:SheetState,
    onEvent:(MainEvent) -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = {
            onEvent(MainEvent.CloseBottomSheet)
        },
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight(0.7f),
    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
               Icon(
                   imageVector = Icons.Rounded.CheckCircle,
                   contentDescription = "",
                   modifier = Modifier.size(100.dp),
                   tint = Color.Green
               )

                Text(
                    text = "Ваша заявка была успешна отправлена на рассмотрение!",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }

    }

}