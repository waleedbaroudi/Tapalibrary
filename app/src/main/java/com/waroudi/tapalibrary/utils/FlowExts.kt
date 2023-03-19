package com.waroudi.tapalibrary.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.convertError(): Flow<T> {
    return flow {
        try {
            collect { value ->
                emit(value)
            }
        } catch (e: Exception) {
            // TODO: report to crashlytics
            throw e
        }
    }
}