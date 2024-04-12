package com.larkes.hackathonpriceapp.data.remote.source

import com.larkes.hackathonpriceapp.data.remote.source.models.AuthRequest
import com.larkes.hackathonpriceapp.data.remote.source.models.TokenRequest
import com.larkes.hackathonpriceapp.data.remote.source.models.TokenResponse
import com.larkes.hackathonpriceapp.domain.model.AuthData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path

class AuthKtorDataSource(
    private val httpClient: HttpClient
) {

    suspend fun sendLogin(authRequest: AuthRequest): TokenResponse {
        val response =  httpClient.post{
            contentType(ContentType.Application.Json)
            url {
                path(LOGIN)
                setBody(authRequest)
            }
        }
        if(response.status.isSuccess().not()){
            val error = response.bodyAsText()
            throw Exception(error)
        }

        return response.body()
    }

    suspend fun sendRegistration(authRequest: AuthRequest){
        val response =  httpClient.post{
            contentType(ContentType.Application.Json)
            url {
                path(REGISTRATION)
                setBody(authRequest)
            }
        }
        if(response.status.isSuccess().not()){
            val error = response.bodyAsText()
            throw Exception(error)
        }

    }

    suspend fun checkAccessToken(tokenRequest: TokenRequest){
        val response =  httpClient.post{
            contentType(ContentType.Application.Json)
            url {
                headers {
                    header("Authorization", "Bearer ${tokenRequest.accessToken}")
                }
                path(CHECK_TOKEN)
            }
        }
        if(response.status.isSuccess().not()){
            val error = response.bodyAsText()
            throw Exception(error)
        }

    }

    suspend fun refreshToken(tokenRequest: TokenRequest){
        val response =  httpClient.post{
            contentType(ContentType.Application.Json)
            url {
                headers {
                    header("Authorization", "Bearer ${tokenRequest.refreshToken}")
                }
                path(REFRESH_TOKEN)
            }
        }
        if(response.status.isSuccess().not()){
            val error = response.bodyAsText()
            throw Exception(error)
        }

    }

    companion object{

        private const val CHECK_TOKEN = "api/auth/signup"
        private const val REFRESH_TOKEN = "api/auth/refresh"
        private const val REGISTRATION = "auth/registration"
        private const val LOGIN = "api/auth/login"

    }

}