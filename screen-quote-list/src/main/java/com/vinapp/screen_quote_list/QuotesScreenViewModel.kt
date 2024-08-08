package com.vinapp.screen_quote_list

import androidx.lifecycle.viewModelScope
import com.vinapp.base_ui_kit.qouteitem.QuoteItemData
import com.vinapp.domain.quotelist.QuoteListInteractor
import com.vinapp.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesScreenViewModel @Inject constructor(
    private val quoteListInteractor: QuoteListInteractor
): BaseViewModel<QuotesScreenState>() {
    override val mutableScreenStateFlow: MutableStateFlow<QuotesScreenState> = MutableStateFlow(QuotesScreenState())

    init {
        subscribeOnQuotes()
    }

    private fun subscribeOnQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            quoteListInteractor.getQuoteListFlow()
                .collect { quoteList ->
                    mutableScreenStateFlow.update { state ->
                        state.copy(
                            quoteList = quoteList.map { QuoteItemData(it) }
                        )
                    }
                }
        }
    }
}