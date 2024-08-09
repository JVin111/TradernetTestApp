package com.vinapp.data_remote_impl.websocket

import android.util.Log
import com.vinapp.base_network.websocket.WebSocketController
import com.vinapp.base_network.websocket.WebSocketSessionState
import com.vinapp.base_network.websocket.income.WebSocketEvent
import com.vinapp.base_network.websocket.income.WebSocketEventData
import com.vinapp.base_network.websocket.outgoing.WebSocketMessageParameters
import com.vinapp.data_remote.entity.serializer.WebSocketEventSerializer
import com.vinapp.data_remote.entity.serializer.WebSocketMessageParametersSerializer
import com.vinapp.data_remote.entity.websocketevent.EventType
import com.vinapp.data_remote.entity.websocketmessage.SubscribeOnQuoteChanges
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.FrameType
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readReason
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Inject

const val WEB_SOCKET_TAG = "WEB_SOCKET"

class WebSocketControllerImpl @Inject constructor(
    private val client: HttpClient
) : WebSocketController {

    private val webSocketScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var socketJob: Job? = null
    private var eventsFlow: MutableSharedFlow<WebSocketEvent?> = MutableStateFlow(null)
    private var sessionStateFlow: MutableStateFlow<WebSocketSession?> = MutableStateFlow(null)
    @OptIn(ExperimentalSerializationApi::class)
    private val format = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }
    override var state: MutableStateFlow<WebSocketSessionState> = MutableStateFlow(WebSocketSessionState.STOPPED)

    init {
        state
            .onEach {
                Log.d(WEB_SOCKET_TAG, "STATE: $it")
            }
            .launchIn(webSocketScope)
    }

    override fun openSession() {
        socketJob = webSocketScope.launch {
            client.webSocket(
                method = HttpMethod.Get,
                host = "wss.tradernet.com",
                port = 8080,
            ) {
                state.emit(WebSocketSessionState.STARTED)
                sessionStateFlow.emit(this)
                incoming.consumeAsFlow()
                    .collect { frame ->
                        when (frame.frameType) {
                            FrameType.TEXT -> {
                                frame as Frame.Text
                                Log.d(WEB_SOCKET_TAG, "Text frame: ${frame.readText()}")
                                val event = format.decodeFromString(WebSocketEventSerializer, frame.readText())
                                eventsFlow.emit(event)
                            }
                            FrameType.BINARY -> {}
                            FrameType.CLOSE -> {
                                Log.d(WEB_SOCKET_TAG, "Close frame: ${(frame as Frame.Close).readReason()}")
                            }
                            FrameType.PING -> {}
                            FrameType.PONG -> {}
                        }
                    }
            }
            state.emit(WebSocketSessionState.STOPPED)
        }
    }

    override fun closeSession() {
        webSocketScope.launch {
            sessionStateFlow.value?.close()
            socketJob?.cancel()
            if (state.value != WebSocketSessionState.PAUSED) {
                state.emit(WebSocketSessionState.STOPPED)
            }
        }
    }

    override fun <T : WebSocketEventData> subscribeOnEvents(params: WebSocketMessageParameters): Flow<T> {
        if (sessionStateFlow.value == null || sessionStateFlow.value?.isActive == false) {
            webSocketScope.launch {
                openSession()
            }
        }
        webSocketScope.launch {
            sessionStateFlow.filterNotNull().collect { session ->
                val message = format.encodeToString(WebSocketMessageParametersSerializer, params)
                session.send(message)
                Log.d(WEB_SOCKET_TAG, "Send message: $message")
            }
        }
        return eventsFlow.filterNotNull()
            .filter { event ->
                val incomingEventType = when (params) {
                    is SubscribeOnQuoteChanges -> EventType.QUOTE_UPDATE
                    else -> null
                }
                event.type == incomingEventType?.incomingCode && event.data != null
            }.map {
                it.data as T
            }
    }
}