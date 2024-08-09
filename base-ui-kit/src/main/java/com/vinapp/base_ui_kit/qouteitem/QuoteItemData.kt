package com.vinapp.base_ui_kit.qouteitem

import com.vinapp.domain.entity.UpdatableQuote
import java.util.Locale

data class QuoteItemData(
    val tickerIcon: String?,
    val tickerTitle: String,
    val subtitle: String,
    val percentageChange: String,
    val isGrowing: Boolean,
    val lastTradePrice: String,
    val priceChange: String
) {
    constructor(quote: UpdatableQuote) : this(
        tickerIcon = null,
        tickerTitle = quote.ticker,
        subtitle = if (!quote.exchangeName.isNullOrBlank() && !quote.securityName.isNullOrBlank()) "${quote.exchangeName} | ${quote.securityName}" else "-",
        percentageChange = buildString {
            quote.percentageChange?.let {
                if (it > 0.0F) append("+")
                append(String.format(Locale.US, "%.2f%%", it))
            } ?: append("0.0%")
        },
        isGrowing = quote.percentageChange?.let { it >= 0.0F } ?: true,
        lastTradePrice = quote.lastTradePrice?.toBigDecimal()?.toString() ?: "-",
        priceChange = quote.changeInPrice?.toBigDecimal()?.toString() ?: "-"
    )
}