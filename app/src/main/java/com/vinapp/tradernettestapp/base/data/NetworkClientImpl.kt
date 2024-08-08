package com.vinapp.tradernettestapp.base.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets

class NetworkClientImpl {
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }
}