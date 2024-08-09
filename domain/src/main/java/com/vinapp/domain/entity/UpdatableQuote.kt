package com.vinapp.domain.entity

data class UpdatableQuote(
    val ticker: String,
    val percentageChange: Float?,
    val exchangeName: String?,
    val securityName: String?,
    val lastTradePrice: Float?,
    val changeInPrice: Float?
)
