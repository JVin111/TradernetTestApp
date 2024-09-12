package com.vinapp.presentation.common.ui_kit.qouteitem

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.EaseInOutExpo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.vinapp.presentation.common.ui_kit.theme.Green
import com.vinapp.presentation.common.ui_kit.theme.Red
import com.vinapp.domain.entity.UpdatableQuote
import kotlinx.coroutines.launch

private const val TICKER_LOGO_URL = "https://tradernet.com/logos/get-logo-by-ticker?ticker="

@Composable
fun QuoteItem(
    modifier: Modifier = Modifier,
    itemData: QuoteItemData
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1F)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Ticker(
                    title = itemData.tickerTitle
                )
                PercentageText(
                    percentage = itemData.percentageChange,
                    isGrowing = itemData.isGrowing
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1F),
                    text = itemData.subtitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 10.sp,
                    color = Color.Gray
                )
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = itemData.lastTradePrice,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 10.sp
                    )
                    Text(
                        text = " ( ${itemData.priceChange} )",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 10.sp
                    )
                }
            }
        }
        Box(
            modifier = Modifier
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 14.dp)
                    .size(24.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

@Composable
private fun PercentageText(
    modifier: Modifier = Modifier,
    percentage: String?,
    isGrowing: Boolean
) {
    var previousValue by remember { mutableStateOf(percentage) }
    val isInitialData by remember(percentage) {
        mutableStateOf(
            if (previousValue == null) {
                previousValue = percentage
                true
            } else {
                false
            }
        )
    }
    var isFirstComposition by remember { mutableStateOf(true) }

    val color = remember(isGrowing) { if (isGrowing) Green else Red }
    val animationSpec = remember {
        tween<Color>(
            durationMillis = 1600,
            easing = EaseInOutExpo
        )
    }
    val animatableBackgroundColor = remember { Animatable(White) }
    val animatableTextColor = remember(percentage != null) { Animatable(color) }

    LaunchedEffect(percentage) {
        if (!isFirstComposition && !isInitialData) {
            launch {
                animatableBackgroundColor.snapTo(color)
                animatableBackgroundColor.animateTo(
                    targetValue = White,
                    animationSpec = animationSpec,
                )
            }
            launch {
                animatableTextColor.snapTo(White)
                animatableTextColor.animateTo(
                    targetValue = color,
                    animationSpec = animationSpec
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        isFirstComposition = false
    }
    BasicText(
        modifier = modifier.then(
            Modifier
                .drawBehind {
                    drawRoundRect(
                        color = animatableBackgroundColor.value,
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                }
                .padding(
                    horizontal = 4.dp,
                    vertical = 1.dp
                )
        ),
        text = percentage ?: "0.00%",
        color = {
            animatableTextColor.value
        }
    )
}

@Composable
private fun Ticker(
    modifier: Modifier = Modifier,
    title: String,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(TICKER_LOGO_URL + title.lowercase())
            .memoryCacheKey(title.lowercase())
            .size(Size.ORIGINAL)
            .build(),
        imageLoader = ImageLoader(LocalContext.current),
    )

    Row(
        modifier = modifier.then(
            Modifier.height(24.dp)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (painter.state is AsyncImagePainter.State.Success && painter.intrinsicSize.maxDimension > 1F) {
            Image(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .sizeIn(
                        minWidth = 0.dp,
                        minHeight = 0.dp,
                        maxWidth = 24.dp,
                        maxHeight = 24.dp
                    ),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Inside,
            )
        }
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QuoteItemPreview() {
    QuoteItem(
        itemData = QuoteItemData(
            UpdatableQuote(
                ticker = "ASD",
                percentageChange = 4.56F,
                exchangeName = "MCX",
                securityName = "Asdfghjkl",
                lastTradePrice = 123.45678F.toBigDecimal(),
                changeInPrice = 0.987F.toBigDecimal()
            )
        )
    )
}