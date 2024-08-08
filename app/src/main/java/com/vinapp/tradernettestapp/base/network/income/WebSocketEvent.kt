package com.vinapp.tradernettestapp.base.network.income

import kotlinx.serialization.Serializable

@Serializable
data class WebSocketEvent(
    val type: String,
    val data: WebSocketEventData?
)
