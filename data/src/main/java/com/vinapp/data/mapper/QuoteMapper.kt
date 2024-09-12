package com.vinapp.data.mapper

import com.vinapp.data.source.local.entity.QuoteEntity
import com.vinapp.data.source.remote.entity.websocketevent.NetworkQuote

object QuoteMapper {
    fun mapNetworkToLocal(
        quote: NetworkQuote
    ) = QuoteEntity(
        ticker = quote.ticker,
        percentageChange = quote.percentageChange,
        exchangeName = quote.exchangeName,
        securityName = quote.securityName,
        lastTradePrice = quote.lastTradePrice,
        changeInPrice = quote.changeInPrice,
        minStep = quote.minStep
    )
}