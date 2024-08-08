package com.vinapp.tradernettestapp.dataremote

import com.vinapp.tradernettestapp.dataremote.entity.NetworkQuote
import kotlinx.coroutines.flow.Flow

interface QuoteRemoteSource {
    fun subscribeOnQuoteUpdates(tickerList: List<String>): Flow<NetworkQuote>
}