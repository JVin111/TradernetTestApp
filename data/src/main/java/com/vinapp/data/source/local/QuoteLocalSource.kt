package com.vinapp.data.source.local

import com.vinapp.data.source.local.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuoteLocalSource {
    suspend fun saveQuote(quote: QuoteEntity)
    suspend fun saveQuoteList(quotes: List<QuoteEntity>)
    fun getQuoteListFlow(): Flow<List<QuoteEntity>>
}