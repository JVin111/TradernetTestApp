package com.vinapp.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S: ScreenState> : ViewModel() {
    protected abstract val mutableScreenStateFlow: MutableStateFlow<S>
    val screenStateFlow: StateFlow<S> get() = mutableScreenStateFlow
}