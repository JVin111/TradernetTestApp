package com.vinapp.base_network.websocket

import javax.inject.Inject

class WebSocketSessionManagerImpl @Inject constructor(
    private val wetSocketController: WebSocketController
) : WebSocketSessionManager {

    override fun closeSession() {
        if (wetSocketController.state.value == WebSocketSessionState.STARTED) {
            wetSocketController.closeSession()
        }
    }

    override fun startSession() {
        wetSocketController.openSession()
    }
}