package com.vinapp.data_local

import com.vinapp.data_local.dao.QuoteDao
import com.vinapp.data_local.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteLocalSourceImpl @Inject constructor(
    private val quoteDao: QuoteDao
) : QuoteLocalSource {
    override suspend fun saveQuote(quote: QuoteEntity) {
        quoteDao.updateQuote(
            ticker = quote.ticker,
            percentageChange = quote.percentageChange,
            exchangeName = quote.exchangeName,
            securityName = quote.securityName,
            lastTradePrice = quote.lastTradePrice,
            changeInPrice = quote.changeInPrice,
        )
    }

    override suspend fun saveQuoteList(quotes: List<QuoteEntity>) {
        quoteDao.insertQuotes(quotes)
    }

    override fun getQuoteListFlow(): Flow<List<QuoteEntity>> {
        return quoteDao.getQuoteListFlow()
    }
}