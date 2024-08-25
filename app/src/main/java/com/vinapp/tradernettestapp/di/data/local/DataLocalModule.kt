package com.vinapp.tradernettestapp.di.data.local

import com.vinapp.data_local.QuoteLocalSource
import com.vinapp.data_local.QuoteLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataLocalModule {

    @ViewModelScoped
    @Binds
    abstract fun bindQuoteLocalSource(impl: QuoteLocalSourceImpl): QuoteLocalSource
}