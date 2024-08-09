package com.vinapp.data_remote.entity.websocketevent

import com.vinapp.base_network.websocket.income.WebSocketEventData
import com.vinapp.domain.entity.UpdatableQuote
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
    fun toDomain() =
        UpdatableQuote(
            ticker = ticker,
            percentageChange = percentageChange,
            exchangeName = exchangeName,
            securityName = securityName,
            lastTradePrice = lastTradePrice,
            changeInPrice = changeInPrice
        )
}
