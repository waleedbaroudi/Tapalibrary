package com.waroudi.tapalibrary.utils

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