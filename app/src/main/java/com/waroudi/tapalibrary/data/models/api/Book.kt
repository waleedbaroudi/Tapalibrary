package com.waroudi.tapalibrary.data.models.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Int,
    val title: String,
    val isbn: String,
    val price: Int,
    val currencyCode: String,
    val author: String
): Parcelable {
    fun getFormattedPrice() = "$price $currencyCode"
    fun getBookCoverUrl() = "https://covers.openlibrary.org/b/ISBN/$isbn-L.jpg"
}
