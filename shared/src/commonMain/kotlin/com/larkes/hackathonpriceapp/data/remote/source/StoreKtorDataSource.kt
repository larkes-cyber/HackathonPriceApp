package com.larkes.hackathonpriceapp.data.remote.source

import com.larkes.hackathonpriceapp.data.remote.source.models.StoreResponse
import com.larkes.hackathonpriceapp.data.remote.source.models.TokenRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path

class StoreKtorDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchStores(tokenRequest:TokenRequest):List<StoreResponse>{

        val response =  httpClient.get{
            contentType(ContentType.Application.Json)
            url {
                path(FETCH_STORES)
                headers {
                    header("Authorization", "Bearer ${tokenRequest.token}")
                }
            }
        }
        if(response.status.isSuccess().not()){
            val error = response.bodyAsText()
            throw Exception(error)
        }
        return response.body()

    }

    companion object{
        private const val FETCH_STORES = "api/stores/stores"
    }

}