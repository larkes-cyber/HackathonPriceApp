package com.larkes.hackathonpriceapp.data.repository

import com.larkes.hackathonpriceapp.data.remote.source.PriceKtorDataSource
import com.larkes.hackathonpriceapp.data.remote.source.models.PerformedPriceRequest
import com.larkes.hackathonpriceapp.data.remote.source.models.TokenRequest
import com.larkes.hackathonpriceapp.domain.model.PerformedPrice
import com.larkes.hackathonpriceapp.domain.model.PriceResult
import com.larkes.hackathonpriceapp.domain.model.ScannedPrice
import com.larkes.hackathonpriceapp.domain.repository.AuthRepository
import com.larkes.hackathonpriceapp.domain.repository.PriceRepository

class PriceRepositoryImpl(
    private val priceKtorDataSource: PriceKtorDataSource,
    private val authRepository: AuthRepository
):PriceRepository {
    override suspend fun performPricePhoto(byteArray: ByteArray): ScannedPrice {
        authRepository.checkAccessToken()
        val token = authRepository.fetchAuthToken() ?: throw Exception("Unathorized user")
        println("${token.accessToken}  %%%%%%% bfgggggf")
        val response = priceKtorDataSource.sendImage(
            byteArray = byteArray,
            tokenRequest = TokenRequest(
                token = token.accessToken,
            )
        )

        return ScannedPrice(
            name = response.name,
            id = response.id,
            category = response.category,
            price = response.price
        )
    }

    override suspend fun performPrice(performedPrice: PerformedPrice): PriceResult {
        authRepository.checkAccessToken()
        val token = authRepository.fetchAuthToken() ?: throw Exception("Unathorized user")
        val response = priceKtorDataSource.sendPerformedPrice(
            performedPrice = PerformedPriceRequest(
                price = performedPrice.price,
                category = performedPrice.category,
                store = performedPrice.store.toInt(),
                name = performedPrice.name
            ),
            tokenRequest = TokenRequest(
                token = token.accessToken
            )
        )

        return PriceResult(
            price = response.price,
            category = response.category,
            name = response.name,
            store = response.store
        )

    }
}