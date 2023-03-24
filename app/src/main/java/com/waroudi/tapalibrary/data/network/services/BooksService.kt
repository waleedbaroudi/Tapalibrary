package com.waroudi.tapalibrary.data.network.services

import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.models.api.TapaResult
import com.waroudi.tapalibrary.data.models.error.TapaServiceError
import com.waroudi.tapalibrary.data.network.api.BookApi
import com.waroudi.tapalibrary.utils.extensions.convertError
import com.waroudi.tapalibrary.utils.extensions.convertErrorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Manages data through the book service
 * @param bookApi a [BookApi] to provide service methods
 */
class BooksService(private val bookApi: BookApi) {

    /**
     * Retrieve all books from the book service
     * @return list of retrieved books
     */
    fun getAllBooks(): Flow<TapaResult<List<Book>>> {
        return flow {
            val response = bookApi.getAllBooks()
            val body = response.body()
            val error = response.convertErrorBody<TapaServiceError>()
            emit(TapaResult(body, error))
        }.convertError()
    }

    /**
     * Retrieve a given book by ID from the book service
     * @param id the ID of the requested book
     * @return the book with the given ID, if it exists
     */
    fun getBookByID(id: String): Flow<TapaResult<Book>> {
        return flow {
            val response = bookApi.getBookById(id)
            val body = response.body()
            val error = response.convertErrorBody<TapaServiceError>()
            emit(TapaResult(body, error))
        }.convertError()
    }
}