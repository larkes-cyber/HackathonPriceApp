package com.larkes.hackathonpriceapp.data.remote.source.models

import kotlinx.serialization.Serializable

@Serializable
class PriceResponse(
    val name:String,
    val price:Float,
    val category:String,
    val store:Int,
    val analyse_result:AnalyseDto
)