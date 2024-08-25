package com.vinapp.domain.quotelist

import com.vinapp.domain.entity.UpdatableQuote
import kotlinx.coroutines.flow.Flow

interface QuoteListRepository {
    fun getQuoteListFlow(tickerList: List<String>): Flow<List<UpdatableQuote>>
}