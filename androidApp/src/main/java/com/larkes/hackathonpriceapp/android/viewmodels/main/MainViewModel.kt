package com.larkes.hackathonpriceapp.android.viewmodels.main

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthAction
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthEvent
import com.larkes.hackathonpriceapp.android.viewmodels.auth.models.AuthState
import com.larkes.hackathonpriceapp.android.viewmodels.main.models.MainAction
import com.larkes.hackathonpriceapp.android.viewmodels.main.models.MainEvent
import com.larkes.hackathonpriceapp.android.viewmodels.main.models.MainState
import com.larkes.hackathonpriceapp.di.InjectUseCase
import com.larkes.hackathonpriceapp.domain.model.PerformedPrice
import com.larkes.hackathonpriceapp.domain.model.ScannedPrice
import com.larkes.hackathonpriceapp.domain.model.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor():ViewModel() {

    private val _mainAction = MutableStateFlow<MainAction?>(null)
    val mainAction: StateFlow<MainAction?> = _mainAction
    private val _mainUIState = MutableStateFlow(MainState())
    val mainUIState: StateFlow<MainState> = _mainUIState

    init {
        fetchStores()
    }
    fun onEvent(event: MainEvent){

        when(event){
            is MainEvent.PhotoTook -> {
                obtainPhotoTook(event.photo)
            }
            is MainEvent.ScannedPriceChanged -> {
                obtainScannedPriceChanged(event.scannedPrice)
            }
            is MainEvent.CloseBottomSheet -> {
                obtainCloseBottomSheet()
            }
            is MainEvent.SelectStore -> {
                obtainSelectStore(event.store)
            }
            is MainEvent.ConfirmPrice -> {
                obtainConfirmPrice()
            }
        }

    }

    private fun obtainConfirmPrice() {
        viewModelScope.launch {

            _mainUIState.value = mainUIState.value.copy(isLoading = true)

            val res = InjectUseCase.usePerformPrice.execute(PerformedPrice(
                price = mainUIState.value.scannedPrice?.price ?: 0f,
                category = mainUIState.value.scannedPrice?.category ?: "",
                name = mainUIState.value.scannedPrice?.name ?: "",
                store = mainUIState.value.selectedStore?.id ?: "",
                ))

            _mainUIState.value = mainUIState.value.copy(isLoading = false)

            _mainAction.value = MainAction.OpenCompletedSheet


        }
    }

    private fun fetchStores(){
        viewModelScope.launch {
            val res = InjectUseCase.useFetchStores.execute()
            _mainUIState.value = mainUIState.value.copy(stores = res.data ?: listOf())
        }
    }
    private fun obtainSelectStore(store:Store) {
        _mainUIState.value = mainUIState.value.copy(selectedStore = store)
    }

    private fun obtainCloseBottomSheet() {
        _mainAction.value = null
        _mainUIState.value = MainState(stores = mainUIState.value.stores)
    }

    private fun obtainScannedPriceChanged(scannedPrice: ScannedPrice) {
        _mainUIState.value = mainUIState.value.copy(scannedPrice = scannedPrice)
    }

    private fun obtainPhotoTook(bitmap:Bitmap) {
        viewModelScope.launch {
            _mainAction.value = MainAction.OpenEditPriceSheet
            _mainUIState.value = mainUIState.value.copy(bitmap = bitmap, isLoading = true)
            val bytes = bitmapToByteArray(bitmap)
            val res = InjectUseCase.useSendPricePhoto.execute(bytes)
            _mainUIState.value = mainUIState.value.copy(error = res.message ?: "", isLoading = false, scannedPrice = res.data)
        }
    }

}

fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}