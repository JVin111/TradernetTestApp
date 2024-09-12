package com.vinapp.data.source.remote.entity.serializer

import com.vinapp.base_network.websocket.outgoing.WebSocketMessageParameters
import com.vinapp.data.source.remote.entity.websocketevent.EventType
import com.vinapp.data.source.remote.entity.websocketmessage.SubscribeOnQuoteChanges
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.encodeToJsonElement

object WebSocketMessageParametersSerializer : KSerializer<WebSocketMessageParameters> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("EventSubscriptionParameters") {
        element<WebSocketMessageParameters?>("data")
    }

    override fun serialize(encoder: Encoder, value: WebSocketMessageParameters) {
        val jsonEncoder = encoder as JsonEncoder
        val jsonArray = when (value) {
            is SubscribeOnQuoteChanges -> {
                buildJsonArray {
                    add(JsonPrimitive(EventType.QUOTE_UPDATE.outgoingCode))
                    add(JsonArray(value.data.map { Json.encodeToJsonElement(it) }))
                }
            }
            else -> throw IllegalStateException("Incorrect WebSocketMessageParameters type")
        }
        jsonEncoder.encodeJsonElement(jsonArray)
    }

    override fun deserialize(decoder: Decoder): WebSocketMessageParameters {
        throw UnsupportedOperationException("Deserialization is not implemented")
    }
}