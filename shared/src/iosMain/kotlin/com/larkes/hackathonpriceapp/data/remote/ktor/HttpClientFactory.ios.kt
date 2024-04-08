package com.larkes.hackathonpriceapp.data.remote.ktor

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO

internal actual class HttpClientFactory actual constructor(){
    actual fun createHttpClient(config: HttpClientConfig<*>.()-> Unit): HttpClient = HttpClient(CIO) {
        config()
    }
}