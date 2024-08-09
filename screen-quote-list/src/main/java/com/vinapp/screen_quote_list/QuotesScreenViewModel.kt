package com.vinapp.screen_quote_list

import com.vinapp.base_ui_kit.qouteitem.QuoteItemData
import com.vinapp.domain.quotelist.QuoteListInteractor
import com.vinapp.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuotesScreenViewModel @Inject constructor(
    private val quoteListInteractor: QuoteListInteractor
): BaseViewModel<QuotesScreenState>() {
    override val mutableScreenStateFlow: MutableStateFlow<QuotesScreenState> = MutableStateFlow(QuotesScreenState())

    private val staticTickerList = listOf("SP500.IDX", "AAPL.US", "RSTI", "GAZP", "MRKZ", "RUAL", "HYDR", "MRKS", "SBER", "FEES", "TGKA", "VTBR", "ANH.US", "VICL.US", "BURG.US", "NBL.US", "YETI.US", "WSFS.US", "NIO.US", "DXC.US", "MIC.US", "HSBC.US", "EXPN.EU", "GSK.EU", "SHP.EU", "MAN.EU", "DB1.EU", "MUV2.EU", "TATE.EU", "KGF.EU", "MGGT.EU", "SGGD.EU")

    init {
        subscribeOnQuotes()
    }

    private fun subscribeOnQuotes() {
        quoteListInteractor.getQuoteListFlowByTickerList(
            tickerList = staticTickerList
        ).onEach { quoteList ->
            mutableScreenStateFlow.update { state ->
                state.copy(
                    quoteList = quoteList.map { QuoteItemData(it) }
                )
            }
        }.launchInIO()
    }
}