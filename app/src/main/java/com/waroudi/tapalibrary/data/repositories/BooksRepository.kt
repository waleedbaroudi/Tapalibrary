package com.waroudi.tapalibrary.data.repositories

import com.waroudi.tapalibrary.data.local.TapaLocalStorage
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.network.services.BooksService
import kotlinx.coroutines.flow.Flow

/**
 * A repository to handle book data locally and through the book service
 * @param service the book service for backend operations
 * @param localStore the local storage for local operations
 */
class BooksRepository(private val service: BooksService, private val localStore: TapaLocalStorage) {
    // Service
    fun getAllBooks(): Flow<List<Book>> = service.getAllBooks()
    fun getBookById(id: String): Flow<Book> = service.getBookByID(id)

    // Local
    fun getFavoritesIds(): List<String> = localStore.getFavoriteIDs()
    fun changeFavoriteState(book: Book) = localStore.changeFavoriteState(book)
    fun isBookFavorite(book: Book) = localStore.isBookFavorite(book)
}