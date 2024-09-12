package com.vinapp.data.source.remote

import com.vinapp.data.source.remote.entity.websocketevent.NetworkQuote
import kotlinx.coroutines.flow.Flow

interface QuoteRemoteSource {
    fun subscribeOnQuoteUpdates(tickerList: List<String>): Flow<NetworkQuote>
}