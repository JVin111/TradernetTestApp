package com.vinapp.presentation.quote_list_screen

import com.vinapp.presentation.common.ScreenState
import com.vinapp.presentation.common.ui_kit.qouteitem.QuoteItemData

data class QuotesScreenState(
    val quoteList: List<QuoteItemData> = emptyList()
) : ScreenState
