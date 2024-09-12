package com.vinapp.presentation.common.ui_kit.qouteitem

import com.vinapp.domain.entity.UpdatableQuote
import java.util.Locale

data class QuoteItemData(
    val tickerIcon: String?,
    val tickerTitle: String,
    val subtitle: String,
    val percentageChange: String?,
    val isGrowing: Boolean,
    val lastTradePrice: String,
    val priceChange: String
) {
    constructor(quote: UpdatableQuote) : this(
        tickerIcon = null,
        tickerTitle = quote.ticker,
        subtitle = if (!quote.exchangeName.isNullOrBlank() && !quote.securityName.isNullOrBlank()) "${quote.exchangeName} | ${quote.securityName}" else "-",
        percentageChange = quote.percentageChange?.let {
            buildString {
                if (it > 0.0F) append("+")
                append(String.format(Locale.US, "%.2f%%", it))
            }
        },
        isGrowing = quote.percentageChange?.let { it >= 0.0F } ?: true,
        lastTradePrice = quote.lastTradePrice?.toString() ?: "-",
        priceChange = quote.changeInPrice?.toString() ?: "-"
    )
}