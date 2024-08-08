package com.vinapp.tradernettestapp.domain.feature.quotelist

import com.vinapp.tradernettestapp.domain.entity.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteListRepository {
    fun getQuoteListFlow(tickerList: List<String>): Flow<List<Quote>>
}