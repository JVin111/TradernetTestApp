package com.vinapp.data_local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vinapp.domain.entity.UpdatableQuote
import java.math.BigDecimal
import java.math.RoundingMode

@Entity(
    tableName = "quote"
)
data class QuoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "ticker")
    val ticker: String,
    @ColumnInfo(name = "percentage_change")
    val percentageChange: Float?,
    @ColumnInfo(name = "exchange_name")
    val exchangeName: String?,
    @ColumnInfo(name = "security_name")
    val securityName: String?,
    @ColumnInfo(name = "last_trade_price")
    val lastTradePrice: BigDecimal?,
    @ColumnInfo(name = "change_in_price")
    val changeInPrice: BigDecimal?,
    @ColumnInfo(name = "min_step")
    val minStep: BigDecimal?
) {
    fun toDomain() = UpdatableQuote(
        ticker = ticker,
        percentageChange = percentageChange,
        exchangeName = exchangeName,
        securityName = securityName,
        lastTradePrice = minStep?.let { step ->
            lastTradePrice?.divide(step, 0, RoundingMode.HALF_UP)?.multiply(step)
        },
        changeInPrice = minStep?.let { step ->
            changeInPrice?.divide(step, 0, RoundingMode.HALF_UP)?.multiply(step)
        }
    )
}
