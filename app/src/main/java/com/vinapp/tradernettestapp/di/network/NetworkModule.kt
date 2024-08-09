package com.vinapp.tradernettestapp.di.network

import com.vinapp.base_network.websocket.WebSocketController
import com.vinapp.base_network.websocket.WebSocketSessionManager
import com.vinapp.base_network.websocket.WebSocketSessionManagerImpl
import com.vinapp.data_remote_impl.websocket.WebSocketControllerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    companion object {
        @Singleton
        @Provides
        fun provideClient(): HttpClient {
            return HttpClient(CIO) {
                install(WebSockets) {
                    contentConverter = KotlinxWebsocketSerializationConverter(Json)
                }
            }
        }
    }

    @Singleton
    @Binds
    abstract fun bindWebSocketController(impl: WebSocketControllerImpl): WebSocketController

    @Singleton
    @Binds
    abstract fun bindWebSocketSessionManger(impl: WebSocketSessionManagerImpl): WebSocketSessionManager
}