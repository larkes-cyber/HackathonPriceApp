package com.larkes.hackathonpriceapp.data.remote.core

import com.larkes.hackathonpriceapp.data.remote.core.HttpClientFactory
import com.larkes.hackathonpriceapp.data.remote.source.AuthKtorDataSource
import com.larkes.hackathonpriceapp.data.remote.source.PriceKtorDataSource
import com.larkes.hackathonpriceapp.data.remote.source.StoreKtorDataSource
import com.larkes.hackathonpriceapp.data.settings.source.AuthSettingsDataSource
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.withTimeout
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

            install(HttpTimeout){
                connectTimeoutMillis = 150000
                requestTimeoutMillis = 300000
            }

            defaultRequest {
                url("https://8f3f-62-183-34-186.ngrok-free.app/")
            }
        }
    }

    single {
        AuthKtorDataSource(get())
    }
    single {
        PriceKtorDataSource(get())

    }
    single {
        StoreKtorDataSource(get())
    }

}