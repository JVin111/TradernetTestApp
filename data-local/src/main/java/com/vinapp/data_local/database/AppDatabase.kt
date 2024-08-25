package com.vinapp.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vinapp.data_local.dao.QuoteDao
import com.vinapp.data_local.entity.QuoteEntity
import com.vinapp.data_local.typeconverter.BigDecimalConverter

const val DATABASE_NAME = "TradernetAppDatabase"

@Database(
    entities = [QuoteEntity::class],
    version = 1
)
@TypeConverters(BigDecimalConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}