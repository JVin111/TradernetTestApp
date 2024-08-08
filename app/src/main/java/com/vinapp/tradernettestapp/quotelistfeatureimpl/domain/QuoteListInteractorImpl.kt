package com.vinapp.tradernettestapp.quotelistfeatureimpl.domain

import com.vinapp.tradernettestapp.domain.feature.quotelist.QuoteListInteractor
import com.vinapp.tradernettestapp.domain.feature.quotelist.QuoteListRepository
import com.vinapp.tradernettestapp.domain.entity.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class QuoteListInteractorImpl(
    private val repository: QuoteListRepository
): QuoteListInteractor {
    override fun getQuoteListFlow(): Flow<List<Quote>> {
//        return repository.getQuoteList().map { list ->
//            list.map { it.toDomain() }
//        }
        return flowOf()
    }
}