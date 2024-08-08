package com.vinapp.data_remote_impl

import com.vinapp.base_network.websocket.WebSocketController
import com.vinapp.data_remote.QuoteRemoteSource
import com.vinapp.data_remote.entity.websocketevent.NetworkQuote
import com.vinapp.data_remote.entity.websocketmessage.SubscribeOnQuoteChanges
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRemoteSourceImpl @Inject constructor(
    private val webSocketController: WebSocketController
): QuoteRemoteSource {

    override fun subscribeOnQuoteUpdates(tickerList: List<String>): Flow<NetworkQuote> {
        return webSocketController.subscribeOnEvents(
            params = SubscribeOnQuoteChanges(
                data = tickerList
            )
        )
    }
}