package com.waroudi.tapalibrary.data.models.error

import com.waroudi.tapalibrary.utils.toSafeString

sealed class TapaLibraryError(var customMessage: String?, cause: Throwable? = null): Exception(customMessage, cause) {
    object NetworkError: TapaLibraryError("Sorry, a network error occurred")
    object UnknownError : TapaLibraryError("Sorry, Something went wrong")
    data class OtherError(val errorMessage: String?, val errorCause: Throwable? = null) : TapaLibraryError(errorMessage, errorCause)

    object BookNotFoundError: TapaLibraryError("Book not found")

    fun safeMessage() = customMessage.toSafeString()
}
