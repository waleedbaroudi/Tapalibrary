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

class BooksService(private val bookApi: BookApi) {

    fun getAllBooks(): Flow<List<Book>> {
        return flow {
            val result = bookApi.getAllBooks()
            emit(result)
        }.convertError()
    }

    fun getBookByID(id: String): Flow<Book> {
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