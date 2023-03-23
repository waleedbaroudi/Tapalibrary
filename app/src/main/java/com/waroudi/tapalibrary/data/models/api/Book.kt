package com.waroudi.tapalibrary.data.models.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Int,
    val title: String,
    val isbn: String,
    @SerializedName("price") val priceAmount: Int,
    val currencyCode: String,
    val author: String,

): Parcelable {

    fun getPrice() = Price(priceAmount, currencyCode)

    fun getBookCoverUrl() = "https://covers.openlibrary.org/b/ISBN/$isbn-L.jpg"
}
