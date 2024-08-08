package com.vinapp.screen_quote_list

import com.vinapp.base_ui_kit.qouteitem.QuoteItemData
import com.vinapp.presentation.ScreenState

data class QuotesScreenState(
    val quoteList: List<QuoteItemData> = emptyList()
) : ScreenState
