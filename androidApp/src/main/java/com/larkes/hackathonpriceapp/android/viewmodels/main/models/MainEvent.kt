package com.larkes.hackathonpriceapp.android.viewmodels.main.models

import android.graphics.Bitmap
import com.larkes.hackathonpriceapp.domain.model.ScannedPrice
import com.larkes.hackathonpriceapp.domain.model.Store

sealed class MainEvent {
    data class PhotoTook(val photo: Bitmap):MainEvent()
    data class ScannedPriceChanged(val scannedPrice: ScannedPrice):MainEvent()
    data object CloseBottomSheet:MainEvent()
    data class SelectStore(val store:Store):MainEvent()
    data object ConfirmPrice:MainEvent()
}