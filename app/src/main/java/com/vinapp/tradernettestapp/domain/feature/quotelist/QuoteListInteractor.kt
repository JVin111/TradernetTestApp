package com.vinapp.tradernettestapp.domain.feature.quotelist

import com.vinapp.tradernettestapp.domain.entity.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteListInteractor {
    fun getQuoteListFlow(): Flow<List<Quote>>
}