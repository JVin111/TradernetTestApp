package com.vinapp.base_ui_kit.qouteitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinapp.base_ui_kit.theme.Green
import com.vinapp.base_ui_kit.theme.Red
import com.vinapp.domain.entity.UpdatableQuote

@Composable
fun QuoteItem(
    modifier: Modifier = Modifier,
    itemData: QuoteItemData
) {
    val shape = remember { RoundedCornerShape(8.dp) }
    val color = remember(itemData.isGrowing) { if (itemData.isGrowing) Green else Red }

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
                    icon = itemData.tickerIcon,
                    title = itemData.tickerTitle
                )
                Box(modifier = Modifier
                    .clip(shape)
                    .background(color)
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = 4.dp,
                            vertical = 1.dp),
                        text = "${itemData.percentageChange}%",
                        color = Color.White,
                    )
                }
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
                    modifier = Modifier.weight(1F),
                    horizontalArrangement = Arrangement.End
                ) {
                    itemData.lastTradePrice?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 10.sp
                        )
                    }
                    itemData.lastTradePrice?.let {
                        Text(
                            text = " ($it)",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

@Composable
private fun Ticker(
    icon: String?,
    title: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .size(24.dp)
            .background(Color.LightGray)
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
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
                lastTradePrice = 1234.56F,
                changeInPrice = 0.987F
            )
        )
    )
}