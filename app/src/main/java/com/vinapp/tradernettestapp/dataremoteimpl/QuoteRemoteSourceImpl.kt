package com.vinapp.tradernettestapp.dataremoteimpl

import com.vinapp.tradernettestapp.base.network.WebSocketController
import com.vinapp.tradernettestapp.dataremote.QuoteRemoteSource
import com.vinapp.tradernettestapp.dataremote.entity.NetworkQuote
import com.vinapp.tradernettestapp.dataremote.entity.QuoteChangeEventSubscriptionParams
import kotlinx.coroutines.flow.Flow

class QuoteRemoteSourceImpl constructor(
    private val webSocketController: WebSocketController
): QuoteRemoteSource {

    override fun subscribeOnQuoteUpdates(tickerList: List<String>): Flow<NetworkQuote> {
        return webSocketController.subscribeOnEvents(
            params = QuoteChangeEventSubscriptionParams(
                data = tickerList
            )
        )
    }
}