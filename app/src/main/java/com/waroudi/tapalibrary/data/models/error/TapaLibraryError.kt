package com.waroudi.tapalibrary.data.models.error

import com.waroudi.tapalibrary.utils.toSafeString

/**
 * A custom error type for TapaLibrary
 * @param customMessage a custom message for readability or descriptiveness
 * @param cause the throwable causing this error
 */
sealed class TapaLibraryError(var customMessage: String?, cause: Throwable? = null): Exception(customMessage, cause) {
    // General Types
    object NetworkError: TapaLibraryError("Sorry, a network error occurred")
    object UnknownError : TapaLibraryError("Sorry, Something went wrong")
    data class OtherError(val errorMessage: String?, val errorCause: Throwable? = null) : TapaLibraryError(errorMessage, errorCause)
    // Case specific types
    object BookNotFoundError: TapaLibraryError("Book not found")

    fun safeMessage() = customMessage.toSafeString("No message")
}
