package com.waroudi.tapalibrary.data.network.services

import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import com.waroudi.tapalibrary.data.network.api.BookApi
import com.waroudi.tapalibrary.utils.ErrorUtils
import com.waroudi.tapalibrary.utils.convertError
import com.waroudi.tapalibrary.utils.extend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

/**
 * Manages data through the book service
 * @param bookApi a [BookApi] to provide service methods
 */
class BooksService(private val bookApi: BookApi) {

    /**
     * Retrieve all books from the book service
     * @return list of retrieved books
     */
    fun getAllBooks(): Flow<List<Book>> {
        return flow {
            val result = bookApi.getAllBooks()
            emit(result)
        }.convertError()
    }

    /**
     * Retrieve a given book by ID from the book service
     * @param id the ID of the requested book
     * @return the book with the given ID, if it exists
     */
    fun getBookByID(id: String): Flow<Book> {
        // Extend the default converter to handle 404 errors for this request
        val converter = ErrorUtils.defaultConverter.extend { error ->
            if (error is HttpException && (error.code() == 404)) {
                TapaLibraryError.BookNotFoundError
            } else {
                throw error
            }
        }
        return flow {
            val result = bookApi.getBookById(id)
            emit(result)
        }.convertError(converter)
    }
}