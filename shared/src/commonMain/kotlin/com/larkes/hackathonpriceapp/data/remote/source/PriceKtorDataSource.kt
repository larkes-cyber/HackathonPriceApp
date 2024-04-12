package com.larkes.hackathonpriceapp.data.remote.source

import com.larkes.hackathonpriceapp.data.remote.source.models.PerformedPriceRequest
import com.larkes.hackathonpriceapp.data.remote.source.models.PriceResponse
import com.larkes.hackathonpriceapp.data.remote.source.models.ScannedPriceResponse
import com.larkes.hackathonpriceapp.data.remote.source.models.TokenRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path

class PriceKtorDataSource(
    private val httpClient: HttpClient
) {

    suspend fun sendImage(byteArray: ByteArray, tokenRequest: TokenRequest): ScannedPriceResponse {
        val response = httpClient.post(SEND_IMAGE){

            setBody(
                MultiPartFormDataContent(

                    formData {
                        header("Authorization", "Bearer ${tokenRequest.accessToken}")
                        append("description", "user_image")
                        append("image", byteArray, Headers.build {
                            append(HttpHeaders.ContentType, "image/png")
                            append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                        })
                    },
                    boundary = "WebAppBoundary"
                )
            )
        }

        if(response.status.isSuccess().not()){
            val error = response.bodyAsText()
            throw Exception(error)
        }

        return response.body()
    }

    suspend fun sendPerformedPrice(performedPrice: PerformedPriceRequest, tokenRequest: TokenRequest): PriceResponse {
        val response =  httpClient.post{
            contentType(ContentType.Application.Json)
            url {
                path(SEND_PERFORMED_PRICE)
                headers {
                    header("Authorization", "Bearer ${tokenRequest.accessToken}")
                    setBody(performedPrice)
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

        private const val SEND_IMAGE = "price/upload_image"
        private const val SEND_PERFORMED_PRICE = "price/send_performed/price"

    }
}