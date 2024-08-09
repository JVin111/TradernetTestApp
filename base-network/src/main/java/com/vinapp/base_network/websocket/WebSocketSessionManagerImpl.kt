package com.vinapp.base_network.websocket

import kotlinx.coroutines.flow.update
import javax.inject.Inject

class WebSocketSessionManagerImpl @Inject constructor(
    private val wetSocketController: WebSocketController
) : WebSocketSessionManager {

    override fun pauseSession() {
        if (wetSocketController.state.value == WebSocketSessionState.STARTED) {
            wetSocketController.state.update { WebSocketSessionState.PAUSED }
        }
        wetSocketController.closeSession()
    }

    override fun restartSession() {
        if (wetSocketController.state.value == WebSocketSessionState.PAUSED) {
            wetSocketController.openSession()
        }
    }
}