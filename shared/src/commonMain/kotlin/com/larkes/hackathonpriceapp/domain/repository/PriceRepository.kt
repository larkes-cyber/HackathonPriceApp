package com.larkes.hackathonpriceapp.domain.repository

import com.larkes.hackathonpriceapp.domain.model.PerformedPrice
import com.larkes.hackathonpriceapp.domain.model.PriceResult
import com.larkes.hackathonpriceapp.domain.model.ScannedPrice

interface PriceRepository {
    suspend fun performPricePhoto(byteArray: ByteArray):ScannedPrice
    suspend fun performPrice(performedPrice: PerformedPrice): PriceResult

}