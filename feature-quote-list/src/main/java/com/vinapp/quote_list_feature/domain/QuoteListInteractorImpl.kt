package com.vinapp.quote_list_feature.domain

import com.vinapp.domain.entity.UpdatableQuote
import com.vinapp.domain.quotelist.QuoteListInteractor
import com.vinapp.domain.quotelist.QuoteListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteListInteractorImpl @Inject constructor(
    private val repository: QuoteListRepository
): QuoteListInteractor {

    override fun getQuoteListFlowByTickerList(tickerList: List<String>): Flow<List<UpdatableQuote>> {
        return repository.getQuoteListFlow(
            tickerList = tickerList
        )
    }
}