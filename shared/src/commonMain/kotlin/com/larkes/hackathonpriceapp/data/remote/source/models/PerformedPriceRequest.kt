package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
class PerformedPriceRequest(
    val price:Float,
    val category: String,
    val store:Int,
    val name:String
)