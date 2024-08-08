package com.vinapp.tradernettestapp.base.network.income

import com.vinapp.tradernettestapp.dataremote.entity.EventType
import com.vinapp.tradernettestapp.dataremote.entity.NetworkQuote
import kotlinx.serialization.KSerializer
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

object WebSocketEventSerializer : KSerializer<WebSocketEvent> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("WebSocketEvent") {
        element<String>("type")
        element<WebSocketEventData?>("data")
    }

    override fun serialize(encoder: Encoder, value: WebSocketEvent) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.type)
            if (value.data != null && value.data is NetworkQuote) {
                encodeSerializableElement(descriptor, 1, NetworkQuote.serializer(), value.data)
            }
        }
    }

    override fun deserialize(decoder: Decoder): WebSocketEvent {
        val jsonDecoder = decoder as JsonDecoder
        val jsonArray = jsonDecoder.decodeJsonElement().jsonArray
        val type = jsonArray[0].jsonPrimitive.content
        val data = try {
            if (jsonArray.size > 1) {
                when (type) {
                    EventType.QUOTE_UPDATE.incomingCode -> jsonDecoder.json.decodeFromJsonElement(
                        NetworkQuote.serializer(), jsonArray[1])
                    else -> null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        return WebSocketEvent(type, data)
    }
}