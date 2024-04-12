package com.larkes.hackathonpriceapp.domain.usecase

import com.larkes.hackathonpriceapp.domain.model.ScannedPrice
import com.larkes.hackathonpriceapp.domain.repository.PriceRepository
import com.larkes.hackathonpriceapp.utils.Resource

class UseSendPricePhoto(
    private val priceRepository: PriceRepository
) {

    suspend fun execute(byteArray: ByteArray):Resource<ScannedPrice>{
        return try {
            Resource.Success(priceRepository.performPricePhoto(byteArray))
        }catch (e:Exception){
            Resource.Error(e.message ?: "")
        }
    }

}