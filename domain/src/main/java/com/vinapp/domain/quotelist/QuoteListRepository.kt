package com.vinapp.domain.quotelist

import com.vinapp.domain.entity.UpdatableQuote
import kotlinx.coroutines.flow.Flow

interface QuoteListRepository {
    fun getQuoteUpdatesFlow(tickerList: List<String>): Flow<UpdatableQuote>
}