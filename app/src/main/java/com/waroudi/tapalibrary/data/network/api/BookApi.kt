package com.waroudi.tapalibrary.data.network.api

import com.waroudi.tapalibrary.data.models.api.Book
import retrofit2.http.GET

interface BookApi {

    @GET("books")
    suspend fun getAllBooks(): List<Book>
}