package com.waroudi.tapalibrary.data.network.api

import com.waroudi.tapalibrary.data.models.Book
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("books")
    suspend fun getAllBooks(): List<Book>
}