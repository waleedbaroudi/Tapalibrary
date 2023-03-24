package com.waroudi.tapalibrary.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.utils.getString
import com.waroudi.tapalibrary.utils.putString
import kotlinx.coroutines.runBlocking

/**
 * Manages data stored locally
 * @param store the [DataStore] that contains local data
 */
class TapaLocalStorage(private val store: DataStore<Preferences>) {

    /**
     * Retrieves a list of IDs of the books marked as favorite
     * @return ID list
     */
    fun getFavoriteIDs(): List<String> {
        return runBlocking {
            store.getString(KEY_FAVORITE_LIST)?.split(",") ?: listOf()
        }
    }

    /**
     * Checks whether a book is marked as favorite
     * @param book the book to check
     * @return the favorite state of the given book
     */
    fun isBookFavorite(book: Book): Boolean {
        return getFavoriteIDs().contains(book.id.toString())
    }

    /**
     * Flip the favorite state of a given book, and returns the new state
     * @param book the book to change the favorite state for
     * @return the new favorite state of the given book
     */
    fun changeFavoriteState(book: Book): Boolean {
        val bookId = book.id.toString()
        val favorites = getFavoriteIDs().toMutableSet()
        val isFavorite = favorites.contains(bookId) 
        favorites.apply {
            if (isFavorite) remove(bookId) else add(bookId)
        }
        return runBlocking {
            store.putString(KEY_FAVORITE_LIST, favorites.joinToString(","))
            favorites.contains(bookId)
        }
    }

    companion object {
        // Keys
        const val KEY_FAVORITE_LIST = "FAVORITE_LIST"
    }
}