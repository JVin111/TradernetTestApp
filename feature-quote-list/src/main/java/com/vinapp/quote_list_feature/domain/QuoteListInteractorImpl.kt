package com.vinapp.quote_list_feature.domain

import com.vinapp.domain.entity.UpdatableQuote
import com.vinapp.domain.quotelist.QuoteListInteractor
import com.vinapp.domain.quotelist.QuoteListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class QuoteListInteractorImpl @Inject constructor(
    private val repository: QuoteListRepository
): QuoteListInteractor {

    private var cachedQuotes: HashMap<String, UpdatableQuote?> = HashMap()
    
    override fun getQuoteListFlowByTickerList(tickerList: List<String>): Flow<List<UpdatableQuote>> {
        cachedQuotes = tickerList.associateWith { null }.toMap(HashMap())
        return subscribeOnQuoteUpdates(tickerList)
    }

    override fun getQuoteListFlow(): Flow<List<UpdatableQuote>> {
        return flowOf()
    }
    
    private fun subscribeOnQuoteUpdates(tickersList: List<String>): Flow<List<UpdatableQuote>> {
        return repository.getQuoteUpdatesFlow(
            tickerList = tickersList
        ).onEach { updatedQuote ->
            saveQuoteUpdates(
                cachedQuote = cachedQuotes[updatedQuote.ticker],
                quoteUpdates = updatedQuote
            )
        }.map {
            cachedQuotes.values.filterNotNull().toList()
        }
    }

    private fun saveQuoteUpdates(cachedQuote: UpdatableQuote?, quoteUpdates: UpdatableQuote) {
        if (cachedQuote != null) {
            cachedQuotes[quoteUpdates.ticker] = cachedQuote.copy(
                percentageChange = quoteUpdates.percentageChange ?: cachedQuote.percentageChange,
                exchangeName = quoteUpdates.exchangeName ?: cachedQuote.exchangeName,
                securityName = quoteUpdates.securityName ?: cachedQuote.securityName,
                lastTradePrice = quoteUpdates.lastTradePrice ?: cachedQuote.lastTradePrice,
                changeInPrice = quoteUpdates.changeInPrice ?: cachedQuote.changeInPrice,
            )
        } else {
            cachedQuotes[quoteUpdates.ticker] = quoteUpdates
        }
    }
}