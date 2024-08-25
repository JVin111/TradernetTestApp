package com.vinapp.quote_list_feature.data

import com.vinapp.data_local.entity.QuoteEntity
import com.vinapp.data_remote.entity.websocketevent.NetworkQuote

object QuoteMapper {
    fun mapNetworkToLocal(
        quote: NetworkQuote
    ) = QuoteEntity(
        ticker = quote.ticker,
        percentageChange = quote.percentageChange,
        exchangeName = quote.exchangeName,
        securityName = quote.securityName,
        lastTradePrice = quote.lastTradePrice,
        changeInPrice = quote.changeInPrice
    )
}