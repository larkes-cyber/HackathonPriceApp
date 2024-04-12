package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(
    val id:String,
    val location:String
)