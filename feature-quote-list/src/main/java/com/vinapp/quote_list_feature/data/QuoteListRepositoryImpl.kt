package com.vinapp.quote_list_feature.data

import com.vinapp.data_remote.QuoteRemoteSource
import com.vinapp.domain.quotelist.QuoteListRepository
import com.vinapp.domain.entity.UpdatableQuote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuoteListRepositoryImpl @Inject constructor(
    private val quoteRemoteSource: QuoteRemoteSource
) : QuoteListRepository {
    
    override fun getQuoteUpdatesFlow(tickerList: List<String>): Flow<UpdatableQuote> {
        return quoteRemoteSource.subscribeOnQuoteUpdates(
            tickerList = tickerList
        ).map { it.toDomain() }
    }
}