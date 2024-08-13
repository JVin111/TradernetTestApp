package com.vinapp.screen_quote_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vinapp.base_ui_kit.qouteitem.QuoteItem
import com.vinapp.base_ui_kit.qouteitem.QuoteItemData

@Composable
fun QuotesScreen() {

    val viewModel: QuotesScreenViewModel = hiltViewModel()
    val state = viewModel.screenStateFlow.collectAsState()

    QuotesScreenContent(
        screenState = state
    )
}

@Composable
private fun QuotesScreenContent(
    screenState: State<QuotesScreenState>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
            )
            .background(Color.White),
        contentPadding = PaddingValues(8.dp)
    ) {
        val quoteItemList = derivedStateOf {
            screenState.value.quoteList
        }.value
        itemsIndexed(
            items = quoteItemList,
            key = { _, item -> item.tickerTitle },
        ) { index, item ->
            QuoteItemWithDivider(
                itemData = item,
                showDivider = index < quoteItemList.lastIndex
            )
        }
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}

@Composable
fun QuoteItemWithDivider(
    modifier: Modifier = Modifier,
    itemData: QuoteItemData,
    showDivider: Boolean,
) {
    Column {
        QuoteItem(
            modifier = modifier,
            itemData = itemData
        )
        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuotesScreenPreview() {
    QuotesScreenContent(
        screenState = remember {
            mutableStateOf(
                QuotesScreenState(
                    quoteList = buildList {
                        repeat(30) {
                            add(
                                QuoteItemData(
                                    tickerIcon = "",
                                    tickerTitle = "TITLE$it",
                                    subtitle = "MCX | Subtitle",
                                    percentageChange = 4.56F.toString(),
                                    isGrowing = true,
                                    lastTradePrice = "1.23456",
                                    priceChange = "0.23456"
                                )
                            )
                        }
                    }
                )
            )
        }
    )
}