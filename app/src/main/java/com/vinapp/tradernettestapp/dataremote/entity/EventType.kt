package com.vinapp.tradernettestapp.dataremote.entity

enum class EventType(val incomingCode: String, val outgoingCode: String) {
    QUOTE_UPDATE("q", "quotes")
}