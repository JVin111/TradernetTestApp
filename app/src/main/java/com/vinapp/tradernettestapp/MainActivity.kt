package com.vinapp.tradernettestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vinapp.base_network.websocket.WebSocketSessionManager
import com.vinapp.screen_quote_list.QuotesScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var webSocketSessionManager: WebSocketSessionManager

    override fun onResume() {
        super.onResume()
        webSocketSessionManager.restartSession()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotesScreen()
        }
    }

    override fun onStop() {
        webSocketSessionManager.pauseSession()
        super.onStop()
    }
}