package com.larkes.hackathonpriceapp.domain.usecase

import com.larkes.hackathonpriceapp.domain.model.PerformedPrice
import com.larkes.hackathonpriceapp.domain.model.PriceResult
import com.larkes.hackathonpriceapp.domain.repository.PriceRepository
import com.larkes.hackathonpriceapp.utils.Resource

class UsePerformPrice(
    private val priceRepository: PriceRepository
) {

    suspend fun execute(performedPrice: PerformedPrice):Resource<PriceResult>{
        return try {
            Resource.Success(priceRepository.performPrice(performedPrice))
        }catch (e:Exception){
            Resource.Error(e.message ?: "")
        }
    }

}