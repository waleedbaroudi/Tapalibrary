package com.waroudi.tapalibrary.data.network.services

import com.waroudi.tapalibrary.data.models.Book
import com.waroudi.tapalibrary.data.network.api.BookApi
import com.waroudi.tapalibrary.utils.convertError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BooksService(private val bookApi: BookApi) {

    fun getAllBooks(): Flow<List<Book>> {
        return flow {
            val result = bookApi.getAllBooks()
            emit(result)
        }.convertError()
    }
}