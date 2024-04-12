package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
class TokenResponse(
    val accessToken:String,
    val refreshToken:String
)