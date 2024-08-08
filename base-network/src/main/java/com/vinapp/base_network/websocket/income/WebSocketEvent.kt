package com.vinapp.base_network.websocket.income

import kotlinx.serialization.Serializable

@Serializable
data class WebSocketEvent(
    val type: String,
    val data: WebSocketEventData?
)
