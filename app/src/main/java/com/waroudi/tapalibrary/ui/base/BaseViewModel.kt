package com.waroudi.tapalibrary.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import com.waroudi.tapalibrary.data.models.state.UiModelState
import com.waroudi.tapalibrary.data.models.state.data
import com.waroudi.tapalibrary.data.models.state.succeeded
import com.waroudi.tapalibrary.utils.doOnError
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class BaseViewModel : ViewModel() {
    protected fun <T> flowWrapper(
        input: Flow<T>,
        mutableStateFlow: MutableStateFlow<UiModelState<T>>,
        cached: Boolean = false
    ) {
        if (cached.and(mutableStateFlow.value.succeeded)) {
            mutableStateFlow.value.data?.let { data ->
                mutableStateFlow.value = UiModelState.None
                mutableStateFlow.value = UiModelState.Success(data).copy()
            }
        } else {
            viewModelScope.launch {
                mutableStateFlow.value = UiModelState.Loading
                input.doOnError {
                    mutableStateFlow.value = UiModelState.Error(it)
                }.collect { result ->
                    mutableStateFlow.value = UiModelState.Success(result)
                }
            }
        }
    }
}