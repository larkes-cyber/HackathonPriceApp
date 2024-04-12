package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
class ScannedPriceResponse(
    val id:String,
    val name:String,
    val category:String,
    val price:Float
)