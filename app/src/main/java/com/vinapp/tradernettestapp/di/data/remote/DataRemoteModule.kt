package com.vinapp.tradernettestapp.di.data.remote

import com.vinapp.data_remote.QuoteRemoteSource
import com.vinapp.data_remote_impl.QuoteRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataRemoteModule {

    @ViewModelScoped
    @Binds
    abstract fun bindQuoteRemoteSource(impl: QuoteRemoteSourceImpl): QuoteRemoteSource
}