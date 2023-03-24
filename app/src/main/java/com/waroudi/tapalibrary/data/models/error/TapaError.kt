package com.waroudi.tapalibrary.data.models.error

import com.waroudi.tapalibrary.utils.extensions.toSafeString

/**
 * A custom error type for TapaLibrary
 * @param customMessage a custom message for readability or descriptiveness
 * @param cause the throwable causing this error
 */
sealed class TapaError(var customMessage: String?, cause: Throwable? = null): Exception(customMessage, cause) {
    // General Types
    object NetworkError: TapaError("Sorry, a network error occurred")
    object UnknownError : TapaError("Sorry, Something went wrong")
    data class OtherError(val errorMessage: String?, val errorCause: Throwable? = null) : TapaError(errorMessage, errorCause)
    // Case specific types
    object BookNotFoundError: TapaError("Book not found")

    open fun safeMessage() = customMessage.toSafeString("No message")
}
