package com.vinapp.domain.quotelist

import com.vinapp.domain.entity.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteListInteractor {
    fun getQuoteListFlow(): Flow<List<Quote>>
}