package com.waroudi.tapalibrary.data.models.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price(val amount: Int, val currencyCode: String) : Parcelable {
    fun getFormattedPrice() = "${getFormattedAmount()} ${getCurrencySymbol()}"

    fun getFormattedAmount(): String {
        return String.format("%,d", amount)
    }

    fun getCurrencySymbol(): String {
        return when(currencyCode) {
            "USD" -> "$"
            "EUR" -> "€"
            "GBP" -> "£"
            else -> currencyCode
        }
    }
}