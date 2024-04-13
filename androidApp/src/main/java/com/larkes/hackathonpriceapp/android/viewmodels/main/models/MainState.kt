package com.larkes.hackathonpriceapp.android.viewmodels.main.models

import android.graphics.Bitmap
import com.larkes.hackathonpriceapp.domain.model.ScannedPrice
import com.larkes.hackathonpriceapp.domain.model.Store

data class MainState(
    val bitmap: Bitmap? = null,
    val error:String = "",
    val isLoading:Boolean = false,
    val scannedPrice: ScannedPrice? = null,
    val stores:List<Store> = listOf(),
    val selectedStore:Store? = null
)