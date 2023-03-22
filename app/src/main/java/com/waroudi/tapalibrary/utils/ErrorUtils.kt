package com.waroudi.tapalibrary.utils

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import retrofit2.HttpException

typealias ErrorConverter = (error: Exception) -> TapaLibraryError

object ErrorUtils {

    val defaultConverter: ErrorConverter = { error ->
        when {
            error is HttpException -> TapaLibraryError.NetworkError
            error.message.notNull() -> TapaLibraryError.OtherError(error.message, error)
            else -> TapaLibraryError.UnknownError
        }
    }
}

fun ErrorConverter.extend(converter: ErrorConverter): ErrorConverter {
    return { error ->
        try {
            converter(error)
        } catch (exc: Exception) {
            invoke(exc)
        }
    }
}

fun Exception.sendToCrashlytics() {
    Firebase.crashlytics.recordException(this)
}