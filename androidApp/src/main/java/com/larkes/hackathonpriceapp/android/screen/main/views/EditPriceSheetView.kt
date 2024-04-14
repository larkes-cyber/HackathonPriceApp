package com.larkes.hackathonpriceapp.android.screen.main.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larkes.hackathonpriceapp.android.components.ExpandList
import com.larkes.hackathonpriceapp.android.components.PrimaryButton
import com.larkes.hackathonpriceapp.android.components.PrimaryTextField
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthEvent
import com.larkes.hackathonpriceapp.android.viewmodels.main.models.MainEvent
import com.larkes.hackathonpriceapp.android.viewmodels.main.models.MainState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPriceSheetView(
    viewState:MainState,
    sheetState:SheetState,
    onEvent:(MainEvent) -> Unit
    ) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit){
        scope.launch {
            sheetState.expand()
        }
    }

    Box(){

        Image(
            bitmap = viewState.bitmap!!.asImageBitmap(),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        ModalBottomSheet(
            onDismissRequest = {
                onEvent(MainEvent.CloseBottomSheet)
            },
            sheetState = sheetState,
            modifier = Modifier.fillMaxHeight(0.7f),
        ) {

            if (viewState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentAlignment = Alignment.Center
                ) {
                  CircularProgressIndicator()

                }
            }

            if(viewState.error.isNotEmpty()){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp), contentAlignment = Alignment.Center) {
                    Text(text = viewState.error, color = Color.Red, fontSize = 24.sp)
                }
            }
            if (viewState.scannedPrice != null && viewState.isLoading.not()) {

                ExpandList(
                    list = viewState.stores,
                    selectedStore = viewState.selectedStore
                ){
                    onEvent(MainEvent.SelectStore(it))
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 15.dp)
                ) {
                    PrimaryTextField(
                        title = "Название",
                        text = viewState.scannedPrice.name,
                        modifier = Modifier.fillMaxWidth(),
                        onChange = {
                            try {
                                onEvent(
                                    MainEvent.ScannedPriceChanged(
                                        viewState.scannedPrice.copy(
                                            name = it
                                        )
                                    )
                                )
                            } catch (e: Exception) {

                            }
                        }
                    )
                    PrimaryTextField(
                        title = "Цена",
                        modifier = Modifier.fillMaxWidth(),
                        text = viewState.scannedPrice.price.toString(),
                        onChange = {
                            try {
                                onEvent(
                                    MainEvent.ScannedPriceChanged(
                                        viewState.scannedPrice.copy(
                                            price = it
                                        )
                                    )
                                )
                            } catch (e: Exception) {

                            }
                        }
                    )
                    PrimaryTextField(
                        title = "Категория",
                        modifier = Modifier.fillMaxWidth(),
                        text = viewState.scannedPrice.category,
                        onChange = {
                            try {
                                onEvent(
                                    MainEvent.ScannedPriceChanged(
                                        viewState.scannedPrice.copy(
                                            category = it
                                        )
                                    )
                                )
                            } catch (e: Exception) {

                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        title = "Отправить",
                        enabled = viewState.isLoading.not()
                    ) {
                        onEvent(MainEvent.ConfirmPrice)
                    }
                }
                Spacer(modifier = Modifier.height(45.dp))
            }

        }

    }

}