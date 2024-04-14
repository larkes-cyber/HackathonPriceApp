package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
class ScannedPriceResponse(
    val id:String,
    val title:String,
    val category:String,
    val price:String,
    val socialPrice:Boolean
)