package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
class TokenResponse(
    val access:String,
    val refresh:String
)