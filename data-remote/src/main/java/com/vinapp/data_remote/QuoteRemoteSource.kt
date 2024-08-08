package com.vinapp.data_remote

import com.vinapp.data_remote.entity.websocketevent.NetworkQuote
import kotlinx.coroutines.flow.Flow

interface QuoteRemoteSource {
    fun subscribeOnQuoteUpdates(tickerList: List<String>): Flow<NetworkQuote>
}