package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val email:String?,
    val number:String?,
    val password:String
)