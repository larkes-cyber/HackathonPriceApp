package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
data class AnalyseDto(
    val res:String,
    val data:AnalyseBodyDto
)

@Serializable
data class AnalyseBodyDto(
    val price:Float,
    val category:String,
    val store:Int,
    val name:String
)