package com.vinapp.tradernettestapp.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.vinapp.base_network.websocket.WebSocketSessionManager
import javax.inject.Inject

class ConnectivityHelper @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val webSocketSessionManager: WebSocketSessionManager
) {
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            webSocketSessionManager.startSession()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            webSocketSessionManager.closeSession()
        }
    }

    fun register() {
        val builder = NetworkRequest.Builder()
        builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    fun unregister() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}