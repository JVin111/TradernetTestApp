package com.vinapp.quote_list_feature.data

import com.vinapp.data_local.QuoteLocalSource
import com.vinapp.data_local.entity.QuoteEntity
import com.vinapp.data_remote.QuoteRemoteSource
import com.vinapp.domain.quotelist.QuoteListRepository
import com.vinapp.domain.entity.UpdatableQuote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuoteListRepositoryImpl @Inject constructor(
    private val quoteRemoteSource: QuoteRemoteSource,
    private val quoteLocalSource: QuoteLocalSource,
) : QuoteListRepository {
    
    override fun getQuoteListFlow(tickerList: List<String>): Flow<List<UpdatableQuote>> {
        subscribeOnQuoteUpdates(tickerList)
        return quoteLocalSource.getQuoteListFlow().map { list ->
            list.map { it.toDomain() }
        }
    }

    private fun subscribeOnQuoteUpdates(
        tickerList: List<String>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            saveInitialValues(tickerList)
            quoteRemoteSource.subscribeOnQuoteUpdates(
                tickerList = tickerList
            ).collect {
                quoteLocalSource.saveQuote(
                    QuoteMapper.mapNetworkToLocal(it)
                )
            }
        }
    }

    private suspend fun saveInitialValues(
        tickerList: List<String>
    ) {
        quoteLocalSource.saveQuoteList(
            tickerList.map {
                QuoteEntity(
                    ticker = it,
                    percentageChange = null,
                    exchangeName = null,
                    securityName = null,
                    lastTradePrice = null,
                    changeInPrice = null
                )
            }
        )
    }
}