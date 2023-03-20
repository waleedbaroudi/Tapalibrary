package com.waroudi.tapalibrary.data.models.error

import com.waroudi.tapalibrary.utils.toSafeString

sealed class TapaLibraryError(var customMessage: String?, cause: Throwable? = null): Exception(customMessage, cause) {
    object UnknownError : TapaLibraryError("Sorry, Something went wrong")

    data class OtherError(val errorMessage: String?, val errorCause: Throwable? = null) : TapaLibraryError(errorMessage, errorCause)

    fun safeMessage() = customMessage.toSafeString()
}
