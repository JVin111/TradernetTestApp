package com.vinapp.tradernettestapp.dataremote.entity

import com.vinapp.tradernettestapp.base.network.outgoing.EventSubscriptionParameters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteChangeEventSubscriptionParams(
    @SerialName("c")
    override val data: List<String>
) : EventSubscriptionParameters