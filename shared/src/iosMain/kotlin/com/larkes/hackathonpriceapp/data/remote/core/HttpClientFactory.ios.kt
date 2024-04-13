package com.larkes.hackathonpriceapp.data.remote.core

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.darwin.Darwin

internal actual class HttpClientFactory actual constructor(){
    actual fun createHttpClient(config: HttpClientConfig<*>.()-> Unit): HttpClient = HttpClient(Darwin) {
        config()
    }
}