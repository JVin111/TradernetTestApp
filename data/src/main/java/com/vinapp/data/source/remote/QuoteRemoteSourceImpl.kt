package com.vinapp.data.source.remote

import com.vinapp.base_network.websocket.WebSocketController
import com.vinapp.data.source.remote.QuoteRemoteSource
import com.vinapp.data.source.remote.entity.websocketevent.NetworkQuote
import com.vinapp.data.source.remote.entity.websocketmessage.SubscribeOnQuoteChanges
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