package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
data class RegRequest(
    val number:String,
    val email:String,
    val password:String
)