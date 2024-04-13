package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val login:String,
    val password:String
)