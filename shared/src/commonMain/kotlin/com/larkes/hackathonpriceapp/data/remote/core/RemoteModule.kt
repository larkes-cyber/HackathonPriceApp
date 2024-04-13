package com.larkes.hackathonpriceapp.data.remote.core

import com.larkes.hackathonpriceapp.data.remote.core.HttpClientFactory
import com.larkes.hackathonpriceapp.data.remote.source.AuthKtorDataSource
import com.larkes.hackathonpriceapp.data.remote.source.PriceKtorDataSource
import com.larkes.hackathonpriceapp.data.remote.source.StoreKtorDataSource
import com.larkes.hackathonpriceapp.data.settings.source.AuthSettingsDataSource
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
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
                url("https://fdc1-62-183-34-186.ngrok-free.app/")
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