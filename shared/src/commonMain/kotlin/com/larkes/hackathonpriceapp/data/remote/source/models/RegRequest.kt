package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
data class RegRequest(
    val telephone:String,
    val email:String,
    val password:String
)