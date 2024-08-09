package com.vinapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

const val VIEW_MODEL_COROUTINE_EXCEPTION_HANDLER = "VIEW_MODEL_COROUTINE_EXCEPTION_HANDLER"

abstract class BaseViewModel<S: ScreenState> : ViewModel() {
    protected abstract val mutableScreenStateFlow: MutableStateFlow<S>
    val screenStateFlow: StateFlow<S> get() = mutableScreenStateFlow

    private val globalHandler = CoroutineExceptionHandler { _, throwable ->
        Log.w(VIEW_MODEL_COROUTINE_EXCEPTION_HANDLER, throwable)
    }

    protected fun <T> Flow<T>.launchInIO(): Job = viewModelScope.launch(Dispatchers.IO + globalHandler) {
        collect()
    }
}