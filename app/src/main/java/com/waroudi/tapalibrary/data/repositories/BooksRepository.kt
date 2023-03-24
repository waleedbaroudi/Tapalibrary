package com.waroudi.tapalibrary.data.repositories

import com.waroudi.tapalibrary.data.local.TapaLocalStorage
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.network.services.BooksService
import kotlinx.coroutines.flow.Flow

class BooksRepository(private val service: BooksService, private val localStore: TapaLocalStorage) {

    fun getAllBooks(): Flow<List<Book>> {
        return service.getAllBooks()
    }

    fun getBookById(id: String): Flow<Book> {
        return service.getBookByID(id)
    }

    // Local
    fun getFavoritesIds(): List<String> = localStore.getFavoriteIDs()
    fun changeFavoriteState(book: Book) = localStore.changeFavoriteState(book)
}