package com.waroudi.tapalibrary.data.network.api

import com.waroudi.tapalibrary.data.models.api.Book
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {

    /**
     * Retrieve all books from the book service
     * @return list of retrieved books
     */
    @GET("books")
    suspend fun getAllBooks(): List<Book>
    /**
     * Retrieve a given book by ID from the book service
     * @param id the ID of the requested book
     * @return the book with the given ID, if it exists
     */
    @GET("book/{id}")
    suspend fun getBookById(@Path("id") id: String): Book
}