package com.vinapp.tradernettestapp.uikit.components.qouteitem

import com.vinapp.tradernettestapp.domain.entity.Quote

data class QuoteItemData(
    val tickerIcon: String?,
    val tickerTitle: String,
    val subtitle: String,
    val percentageChange: Float,
    val valueChange: String
) {
    constructor(domainQuote: Quote) : this(
        tickerIcon = null,
        tickerTitle = domainQuote.ticker,
        subtitle = "${domainQuote.exchangeName} | ${domainQuote.securityName}",
        percentageChange = domainQuote.percentageChange ?: 0.0F,
        valueChange = "${domainQuote.lastTradePrice} (${domainQuote.changeInPrice})"
    )
}