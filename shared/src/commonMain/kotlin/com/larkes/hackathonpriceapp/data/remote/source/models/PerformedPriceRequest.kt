package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
class PerformedPriceRequest(
    val price:String,
    val category: String,
    val location:String,
    val name:String
)