package com.waroudi.tapalibrary.data.network.api

import com.waroudi.tapalibrary.data.models.api.Book
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {

    @GET("books")
    suspend fun getAllBooks(): List<Book>

    @GET("book/{id}")
    suspend fun getBookById(@Path("id") id: String): Book
}