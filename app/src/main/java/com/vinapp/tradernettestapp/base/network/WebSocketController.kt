package com.vinapp.tradernettestapp.base.network

import com.vinapp.tradernettestapp.base.network.outgoing.EventSubscriptionParameters
import com.vinapp.tradernettestapp.base.network.income.WebSocketEventData
import kotlinx.coroutines.flow.Flow

interface WebSocketController {
    fun openSession()
    fun closeSession()
    fun <T : WebSocketEventData> subscribeOnEvents(params: EventSubscriptionParameters): Flow<T>
}