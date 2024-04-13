package com.larkes.hackathonpriceapp.domain.model

data class PriceResult(
    val price:Float,
    val category:String,
    val store:Int,
    val name:String
)