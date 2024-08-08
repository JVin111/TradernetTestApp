package com.vinapp.tradernettestapp.base.network.outgoing

import com.vinapp.tradernettestapp.dataremote.entity.EventType
import com.vinapp.tradernettestapp.dataremote.entity.QuoteChangeEventSubscriptionParams
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

object EventSubscriptionParametersSerializer : KSerializer<EventSubscriptionParameters> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("EventSubscriptionParameters") {
        element<EventSubscriptionParameters?>("data")
    }

    override fun serialize(encoder: Encoder, value: EventSubscriptionParameters) {
        val jsonEncoder = encoder as JsonEncoder
        val jsonArray = when (value) {
            is QuoteChangeEventSubscriptionParams -> {
                buildJsonArray {
                    add(JsonPrimitive(EventType.QUOTE_UPDATE.outgoingCode))
                    add(JsonArray(value.data.map { Json.encodeToJsonElement(it) }))
                }
            }
            else -> throw IllegalStateException("Incorrect EventSubscriptionParameters type")
        }
        jsonEncoder.encodeJsonElement(jsonArray)
    }

    override fun deserialize(decoder: Decoder): EventSubscriptionParameters {
        throw UnsupportedOperationException("Deserialization is not implemented")
    }
}