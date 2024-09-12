package com.vinapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vinapp.data.source.local.dao.QuoteDao
import com.vinapp.data.source.local.entity.QuoteEntity
import com.vinapp.data.source.local.typeconverter.BigDecimalConverter

const val DATABASE_NAME = "TradernetAppDatabase"

@Database(
    entities = [QuoteEntity::class],
    version = 1
)
@TypeConverters(BigDecimalConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}