package com.larkes.hackathonpriceapp.data.repository

import com.larkes.hackathonpriceapp.data.remote.source.StoreKtorDataSource
import com.larkes.hackathonpriceapp.data.remote.source.models.TokenRequest
import com.larkes.hackathonpriceapp.domain.model.Store
import com.larkes.hackathonpriceapp.domain.repository.AuthRepository
import com.larkes.hackathonpriceapp.domain.repository.StoreRepository

class StoreRepositoryImpl(
    private val storeKtorDataSource: StoreKtorDataSource,
    private val authRepository: AuthRepository
):StoreRepository {
    override suspend fun fetchStores(): List<Store> {
        val token = authRepository.fetchAuthToken() ?: throw Exception("Unathorized user")
        authRepository.checkAccessToken()


        return storeKtorDataSource.fetchStores(tokenRequest = TokenRequest(token.accessToken)).map { Store(
            id = it.id,
            location = it.location,
            region = it.region,
            email = it.email,
            name = it.name
        ) }
    }
}