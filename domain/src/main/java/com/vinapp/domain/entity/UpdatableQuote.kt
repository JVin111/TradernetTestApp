package com.vinapp.domain.entity

import java.math.BigDecimal

data class UpdatableQuote(
    val ticker: String,
    val percentageChange: Float?,
    val exchangeName: String?,
    val securityName: String?,
    val lastTradePrice: BigDecimal?,
    val changeInPrice: BigDecimal?
)
