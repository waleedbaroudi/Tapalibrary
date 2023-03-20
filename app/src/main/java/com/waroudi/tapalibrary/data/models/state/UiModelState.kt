package com.waroudi.tapalibrary.data.models.state

import com.waroudi.tapalibrary.data.models.error.TapaLibraryError

sealed class UiModelState<out R> {
    data class Success<out T>(val data: T) : UiModelState<T>()
    data class Error(val exception: TapaLibraryError) : UiModelState<Nothing>()
    object Loading : UiModelState<Nothing>()
    object None : UiModelState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
            is None -> "Pending State"
        }
    }
}