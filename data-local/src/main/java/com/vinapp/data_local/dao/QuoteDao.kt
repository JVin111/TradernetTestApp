package com.vinapp.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vinapp.data_local.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuotes(quotes: List<QuoteEntity>)

    @Query("UPDATE quote SET " +
            "percentage_change = CASE WHEN :percentageChange IS NOT NULL THEN :percentageChange ELSE percentage_change END, " +
            "exchange_name = CASE WHEN :exchangeName IS NOT NULL THEN :exchangeName ELSE exchange_name END, " +
            "security_name = CASE WHEN :securityName IS NOT NULL THEN :securityName ELSE security_name END, " +
            "last_trade_price = CASE WHEN :lastTradePrice IS NOT NULL THEN :lastTradePrice ELSE last_trade_price END, " +
            "change_in_price = CASE WHEN :changeInPrice IS NOT NULL THEN :changeInPrice ELSE change_in_price END, " +
            "min_step = CASE WHEN :minStep IS NOT NULL THEN :minStep ELSE min_step END " +
            "WHERE ticker = :ticker")
    suspend fun updateQuote(
        ticker: String,
        percentageChange: Float?,
        exchangeName: String?,
        securityName: String?,
        lastTradePrice: BigDecimal?,
        changeInPrice: BigDecimal?,
        minStep: BigDecimal?,
    )

    @Query("SELECT * FROM quote ORDER BY ticker")
    fun getQuoteListFlow(): Flow<List<QuoteEntity>>
}