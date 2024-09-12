package com.vinapp.tradernettestapp.di.data.local

import android.app.Application
import androidx.room.Room
import com.vinapp.data.source.local.dao.QuoteDao
import com.vinapp.data.source.local.database.AppDatabase
import com.vinapp.data.source.local.database.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    companion object {
        @Singleton
        @Provides
        fun provideDatabase(application: Application): AppDatabase =
            Room
                .databaseBuilder(
                    application.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME,
                )
                .fallbackToDestructiveMigration()
                .build()

        @Singleton
        @Provides
        fun provideQuoteDao(database: AppDatabase): QuoteDao = database.quoteDao()
    }
}