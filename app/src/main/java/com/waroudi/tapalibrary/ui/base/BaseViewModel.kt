package com.waroudi.tapalibrary.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import com.waroudi.tapalibrary.data.models.state.UiModelState
import com.waroudi.tapalibrary.data.models.state.data
import com.waroudi.tapalibrary.data.models.state.succeeded
import com.waroudi.tapalibrary.utils.doOnError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected fun <T> flowWrapper(
        input: Flow<T>,
        mutableStateFlow: MutableStateFlow<UiModelState<T>>,
        cached: Boolean = false
    ) {
        viewModelScope.launch {
            if (cached.and(mutableStateFlow.value.succeeded)) {
                mutableStateFlow.value.data?.let { data ->
                    input.collect {
                        mutableStateFlow.value = UiModelState.None
                        mutableStateFlow.value = UiModelState.Success(data).copy()
                    }
                }
            } else {
                mutableStateFlow.value = UiModelState.Loading
                input.doOnError {
                    mutableStateFlow.value = UiModelState.Error(TapaLibraryError.OtherError(it.message, it))
                }.collect { result ->
                    mutableStateFlow.value = UiModelState.Success(result)
                }
            }
        }
    }
}