package com.vinapp.tradernettestapp.di.feature.quotelist

import com.vinapp.domain.quotelist.QuoteListInteractor
import com.vinapp.domain.quotelist.QuoteListRepository
import com.vinapp.quote_list_feature.data.QuoteListRepositoryImpl
import com.vinapp.quote_list_feature.domain.QuoteListInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class QuoteListModule {

    @ViewModelScoped
    @Binds
    abstract fun bindQuoteListInteractor(impl: QuoteListInteractorImpl): QuoteListInteractor

    @ViewModelScoped
    @Binds
    abstract fun bindQuoteListRepository(impl: QuoteListRepositoryImpl): QuoteListRepository
}