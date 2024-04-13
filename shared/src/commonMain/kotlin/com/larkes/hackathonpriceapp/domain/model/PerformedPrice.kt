package com.larkes.hackathonpriceapp.domain.model

data class PerformedPrice(
    val price:Float,
    val category: String,
    val store:String,
    val name:String
)