package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    val token:String
)