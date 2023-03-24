package com.waroudi.tapalibrary.data.models.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Book model
 */
@Parcelize
data class Book(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("isbn") val isbn: String,
    @SerializedName("price") val priceAmount: Int,
    @SerializedName("currencyCode") val currencyCode: String,
    @SerializedName("author") val author: String,
    @SerializedName("description") val description: String?
): Parcelable {

    /**
     * Gets a [Price] object from this book's price amount and currency
     * @return a [Price] object
     */
    fun getPrice() = Price(priceAmount, currencyCode)

    /**
     * Gets a URL to the cover image of this book based on the ISBN
     * @return Book cover image URL
     */
    fun getBookCoverUrl() = "https://covers.openlibrary.org/b/ISBN/$isbn-L.jpg"
}
