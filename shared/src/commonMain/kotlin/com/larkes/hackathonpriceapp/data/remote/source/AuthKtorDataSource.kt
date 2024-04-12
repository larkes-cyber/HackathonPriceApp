package com.larkes.hackathonpriceapp.data.remote.source

import com.larkes.hackathonpriceapp.data.remote.source.models.AuthRequest
import com.larkes.hackathonpriceapp.domain.model.AuthData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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

    suspend fun sendLogin(authRequest: AuthRequest):String{
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

    companion object{

        private const val LOGIN = "auth/login"
        private const val REGISTRATION = "auth/registration"

    }

}