package com.vinapp.domain.quotelist

import com.vinapp.domain.entity.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteListRepository {
    fun getQuoteListFlow(tickerList: List<String>): Flow<List<Quote>>
}