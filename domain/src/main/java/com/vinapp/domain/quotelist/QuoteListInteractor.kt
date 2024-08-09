package com.vinapp.domain.quotelist

import com.vinapp.domain.entity.UpdatableQuote
import kotlinx.coroutines.flow.Flow

interface QuoteListInteractor {
    fun getQuoteListFlowByTickerList(tickerList: List<String>): Flow<List<UpdatableQuote>>
}