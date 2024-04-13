package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val login:String,
    val password:String
)