package com.waroudi.tapalibrary.data.repositories

import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.network.services.BooksService
import kotlinx.coroutines.flow.Flow

class BooksRepository(private val service: BooksService) {

    fun getAllBooks(): Flow<List<Book>> {
        return service.getAllBooks()
    }

    fun getBookById(id: String): Flow<Book> {
        return service.getBookByID(id)
    }
}