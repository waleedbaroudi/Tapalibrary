package com.waroudi.tapalibrary.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.utils.getString
import com.waroudi.tapalibrary.utils.putString
import kotlinx.coroutines.runBlocking

class TapaLocalStorage(private val store: DataStore<Preferences>) {

    fun getFavoriteIDs(): List<String> {
        return runBlocking {
            store.getString(KEY_FAVORITE_LIST)?.split(",") ?: listOf()
        }
    }

    fun isBookFavorite(book: Book): Boolean {
        return getFavoriteIDs().contains(book.id.toString())
    }

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