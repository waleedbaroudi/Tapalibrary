package com.waroudi.tapalibrary.utils

import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Attempts to collects the current flow. In case of failure, converts the error, reports it and throws it
 * @param converter an error converter. The default converter if none is given
 * @return a flow resulting from collecting the current flow
 * @throws TapaLibraryError
 */
fun <T> Flow<T>.convertError(converter: ErrorConverter = ErrorUtils.defaultConverter): Flow<T> {
    return flow {
        try {
            collect { value ->
                emit(value)
            }
        } catch (e: Exception) {
            val tapaError = converter(e)
            tapaError.sendToCrashlytics()
            throw tapaError
        }
    }
}

/**
 * Attempts to collects the current flow, handles errors caught in the process
 * @param onError a lambda to handle errors, if any
 * @return a flow resulting from collecting the current flow
 */
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