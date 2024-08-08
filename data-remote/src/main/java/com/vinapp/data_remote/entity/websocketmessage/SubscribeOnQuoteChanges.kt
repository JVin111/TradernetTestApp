package com.vinapp.data_remote.entity.websocketmessage

import com.vinapp.base_network.websocket.outgoing.WebSocketMessageParameters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubscribeOnQuoteChanges(
    @SerialName("c")
    override val data: List<String>
) : WebSocketMessageParameters