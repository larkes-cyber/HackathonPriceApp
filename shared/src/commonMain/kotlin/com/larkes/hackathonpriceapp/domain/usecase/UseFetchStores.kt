package com.larkes.hackathonpriceapp.domain.usecase

import com.larkes.hackathonpriceapp.domain.model.Store
import com.larkes.hackathonpriceapp.domain.repository.StoreRepository
import com.larkes.hackathonpriceapp.utils.Resource

class UseFetchStores(
    private val storeRepository: StoreRepository
) {

    suspend fun execute():Resource<List<Store>>{
        return try {
            Resource.Success(storeRepository.fetchStores())
        }catch (e:Exception){
            Resource.Error(e.message ?: "")
        }
    }

}