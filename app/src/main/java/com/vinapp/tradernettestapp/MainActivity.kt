package com.vinapp.tradernettestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vinapp.base_network.websocket.WebSocketSessionManager
import com.vinapp.screen_quote_list.QuotesScreen
import com.vinapp.tradernettestapp.utils.ConnectivityHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var webSocketSessionManager: WebSocketSessionManager

    @Inject
    lateinit var connectivityHelper: ConnectivityHelper

    override fun onResume() {
        super.onResume()
        webSocketSessionManager.startSession()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityHelper.register()
        enableEdgeToEdge()
        setContent {
            QuotesScreen()
        }
    }

    override fun onStop() {
        webSocketSessionManager.closeSession()
        super.onStop()
    }

    override fun onDestroy() {
        connectivityHelper.unregister()
        super.onDestroy()
    }
}