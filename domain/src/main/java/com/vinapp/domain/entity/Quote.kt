package com.vinapp.domain.entity

data class Quote(
    val ticker: String,
    val percentageChange: Float,
    val exchangeName: String,
    val securityName: String,
    val lastTradePrice: Float,
    val changeInPrice: Float
)
