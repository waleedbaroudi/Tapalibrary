package com.waroudi.tapalibrary.utils

import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.convertError(converter: ErrorConverter = ErrorUtils.defaultConverter): Flow<T> {
    return flow {
        try {
            collect { value ->
                emit(value)
            }
        } catch (e: Exception) {
            // TODO: report to crashlytics
            throw converter(e)
        }
    }
}

fun <T> Flow<T>.doOnError(onError: (TapaLibraryError) -> Unit): Flow<T> {
    return flow {
        try {
            collect { value ->
                emit(value)
            }
        } catch (e: TapaLibraryError) {
            onError(e)
        }
    }
}