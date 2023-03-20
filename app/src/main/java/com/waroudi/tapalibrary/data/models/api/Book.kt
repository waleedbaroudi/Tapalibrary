package com.waroudi.tapalibrary.data.models.api

data class Book(
    val id: Int,
    val title: String,
    val isbn: String,
    val price: Int,
    val currencyCode: String,
    val author: String
) {
    fun getFormattedPrice() = "$price $currencyCode"
}
