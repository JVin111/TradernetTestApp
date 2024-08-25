package com.vinapp.data_local

import com.vinapp.data_local.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuoteLocalSource {
    suspend fun saveQuote(quote: QuoteEntity)
    suspend fun saveQuoteList(quotes: List<QuoteEntity>)
    fun getQuoteListFlow(): Flow<List<QuoteEntity>>
}