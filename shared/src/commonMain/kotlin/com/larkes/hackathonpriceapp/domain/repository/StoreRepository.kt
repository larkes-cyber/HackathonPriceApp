package com.larkes.hackathonpriceapp.domain.repository

import com.larkes.hackathonpriceapp.domain.model.Store

interface StoreRepository {

    suspend fun fetchStores():List<Store>

}