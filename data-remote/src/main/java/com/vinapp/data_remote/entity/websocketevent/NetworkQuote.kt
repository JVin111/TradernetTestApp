package com.vinapp.data_remote.entity.websocketevent

import com.vinapp.base_network.websocket.income.WebSocketEventData
import com.vinapp.domain.entity.Quote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkQuote(
    @SerialName("c") val ticker: String,
    @SerialName("pcp") val percentageChange: Float?,
    @SerialName("ltr") val exchangeName: String?,
    @SerialName("name") val securityName: String?,
    @SerialName("ltp") val lastTradePrice: Float?,
    @SerialName("chg") val changeInPrice: Float?
) : WebSocketEventData {
    fun toDomain(existingQuote: Quote? = null) =
        Quote(
            ticker = ticker,
            percentageChange = percentageChange ?: existingQuote?.percentageChange ?: 0.0F,
            exchangeName = exchangeName ?: existingQuote?.exchangeName ?: "",
            securityName = securityName ?: existingQuote?.securityName ?: "",
            lastTradePrice = lastTradePrice ?: existingQuote?.lastTradePrice ?: 0.0F,
            changeInPrice = changeInPrice ?: existingQuote?.changeInPrice ?: 0.0F
        )
}
