package com.waroudi.tapalibrary.utils

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.waroudi.tapalibrary.data.models.error.TapaError
import com.waroudi.tapalibrary.utils.extensions.notNull
import retrofit2.HttpException

/**
 * A lambda that converts an [Exception] into a [TapaError]
 */
typealias ErrorConverter = (error: Exception) -> TapaError

object ErrorUtils {
    /**
     * Default [ErrorConverter] for general errors
     */
    val defaultConverter: ErrorConverter = { error ->
        when {
            error is HttpException -> {
                TapaError.NetworkError
            }
            error.message.notNull() -> TapaError.OtherError(error.message, error)
            else -> TapaError.UnknownError
        }
    }
}

/**
 * Extension for adding conversion rules to the current converter
 */
fun ErrorConverter.extend(converter: ErrorConverter): ErrorConverter {
    return { error ->
        try {
            converter(error)
        } catch (exc: Exception) {
            invoke(exc)
        }
    }
}

/**
 * Reports this exception to Crashlytics
 */
fun Exception.sendToCrashlytics() {
    Firebase.crashlytics.recordException(this)
}