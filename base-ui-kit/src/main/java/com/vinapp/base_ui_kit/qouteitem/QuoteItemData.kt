package com.vinapp.base_ui_kit.qouteitem

import com.vinapp.domain.entity.Quote

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
        percentageChange = domainQuote.percentageChange,
        valueChange = "${domainQuote.lastTradePrice} (${domainQuote.changeInPrice})"
    )
}