package com.vinapp.data.source.remote.entity.websocketevent

import com.vinapp.base_network.websocket.income.WebSocketEventData
import com.vinapp.data.source.remote.entity.serializer.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class NetworkQuote(
    @SerialName("c")
    val ticker: String,
    @SerialName("pcp")
    val percentageChange: Float?,
    @SerialName("ltr")
    val exchangeName: String?,
    @SerialName("name")
    val securityName: String?,
    @SerialName("ltp")
    @Serializable(with = BigDecimalSerializer::class)
    val lastTradePrice: BigDecimal?,
    @SerialName("chg")
    @Serializable(with = BigDecimalSerializer::class)
    val changeInPrice: BigDecimal?,
    @SerialName("min_step")
    @Serializable(with = BigDecimalSerializer::class)
    val minStep: BigDecimal?
) : WebSocketEventData