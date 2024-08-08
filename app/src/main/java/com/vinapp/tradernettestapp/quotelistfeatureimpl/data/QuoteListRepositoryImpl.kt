package com.vinapp.tradernettestapp.quotelistfeatureimpl.data

import com.vinapp.tradernettestapp.domain.feature.quotelist.QuoteListRepository
import com.vinapp.tradernettestapp.dataremote.QuoteRemoteSource
import com.vinapp.tradernettestapp.domain.entity.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuoteListRepositoryImpl constructor(
    private val quoteRemoteSource: QuoteRemoteSource
) : QuoteListRepository {

    private val tickers: HashMap<String, Quote?> = listOf("SP500.IDX", "AAPL.US", "RSTI", "GAZP", "MRKZ", "RUAL", "HYDR", "MRKS", "SBER", "FEES", "TGKA", "VTBR", "ANH.US", "VICL.US", "BURG.US", "NBL.US", "YETI.US", "WSFS.US", "NIO.US", "DXC.US", "MIC.US", "HSBC.US", "EXPN.EU", "GSK.EU", "SHP.EU", "MAN.EU", "DB1.EU", "MUV2.EU", "TATE.EU", "KGF.EU", "MGGT.EU", "SGGD.EU")
        .associateWith { null }.toMap(HashMap())

    override fun getQuoteListFlow(tickerList: List<String>): Flow<List<Quote>> {
        return quoteRemoteSource.subscribeOnQuoteUpdates(
            tickerList = tickers.keys.toList()
        ).map { update ->
            val quote = tickers[update.ticker]
            val updatedQuote = update.toDomain(quote)
            tickers[update.ticker] = updatedQuote
            tickers.values.filterNotNull().toList()
        }
    }
}