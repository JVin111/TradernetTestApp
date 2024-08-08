package com.vinapp.screen_quote_list

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vinapp.base_ui_kit.qouteitem.QuoteItem
import com.vinapp.base_ui_kit.qouteitem.QuoteItemData
import com.vinapp.data_remote_impl.QuoteRemoteSourceImpl
import com.vinapp.data_remote_impl.websocket.WebSocketControllerImpl
import com.vinapp.domain.quotelist.QuoteListInteractor
import com.vinapp.quote_list_feature.data.QuoteListRepositoryImpl
import com.vinapp.quote_list_feature.domain.QuoteListInteractorImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json

@Composable
fun QuotesScreen() {

    val viewModel: QuotesScreenViewModel = hiltViewModel()
    val state = viewModel.screenStateFlow.collectAsState().value

    QuotesScreenContent(state.quoteList)
}

@Composable
private fun QuotesScreenContent(
    quoteItemList: List<QuoteItemData>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
            ),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        itemsIndexed(
            items = quoteItemList,
            key = { _, item -> item.tickerTitle }
        ) { index, item ->
            QuoteItem(
                itemData = item
            )
            if (index < quoteItemList.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                )
            }
        }
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuotesScreenPreview() {
    QuotesScreenContent(
        buildList {
            repeat(30) {
                add(QuoteItemData(
                    tickerIcon = "",
                    tickerTitle = "TITLE$it",
                    subtitle = "MCX | Subtitle",
                    percentageChange = 4.56F,
                    valueChange = "1.23456 (0.23456)"
                ))
            }
        }
    )
}