package com.larkes.hackathonpriceapp.data.remote

import com.larkes.hackathonpriceapp.data.remote.ktor.HttpClientFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val remoteModule = module {

    single {
        HttpClientFactory().createHttpClient {
            install(ContentNegotiation){
                json(Json{
                    prettyPrint = true
                    isLenient = true
                })
            }

            defaultRequest {
                url("http://192.168.0.102:8080")
            }
        }
    }

}