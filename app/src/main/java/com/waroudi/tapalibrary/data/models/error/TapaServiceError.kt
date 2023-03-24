package com.waroudi.tapalibrary.data.models.error

import com.google.gson.annotations.SerializedName
import com.waroudi.tapalibrary.utils.extensions.toSafeString

data class TapaServiceError(@SerializedName("message") val errorMessage: String?) : TapaError(errorMessage, null) {
    override fun safeMessage(): String {
        return errorMessage.toSafeString("No message")
    }
}