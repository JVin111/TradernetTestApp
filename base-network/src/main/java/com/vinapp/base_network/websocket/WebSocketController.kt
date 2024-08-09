package com.vinapp.base_network.websocket

import com.vinapp.base_network.websocket.outgoing.WebSocketMessageParameters
import com.vinapp.base_network.websocket.income.WebSocketEventData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface WebSocketController {
    var state: MutableStateFlow<WebSocketSessionState>
    fun openSession()
    fun closeSession()
    fun <T : WebSocketEventData> subscribeOnEvents(params: WebSocketMessageParameters): Flow<T>
}