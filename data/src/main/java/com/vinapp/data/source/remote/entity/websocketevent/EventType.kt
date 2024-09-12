package com.vinapp.data.source.remote.entity.websocketevent

enum class EventType(val incomingCode: String, val outgoingCode: String) {
    QUOTE_UPDATE("q", "quotes")
}