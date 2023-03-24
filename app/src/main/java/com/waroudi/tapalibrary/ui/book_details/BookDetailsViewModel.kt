package com.waroudi.tapalibrary.ui.book_details

import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.models.state.UiModelState
import com.waroudi.tapalibrary.data.models.state.data
import com.waroudi.tapalibrary.data.repositories.BooksRepository
import com.waroudi.tapalibrary.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BookDetailsViewModel(private val booksRepository: BooksRepository) : BaseViewModel() {
    private val _book = MutableStateFlow<UiModelState<Book>>(UiModelState.None)
    val book: StateFlow<UiModelState<Book>> get() = _book

    fun getBookById(id: String) {
        flowWrapper(booksRepository.getBookById(id), _book)
    }

    /**
     * Sets the book model to a given [Book]
     * @param book the book to be set
     */
    fun setBook(book: Book) {
        _book.value = UiModelState.Success(book)
    }

    /**
     * Checks whether the current book model is marked as favorite
     */
    fun isBookFavorite() =
        _book.value.data?.let { booksRepository.isBookFavorite(it) } ?: false

    /**
     * Changes the favorite state of the current book
     */
    fun changeFavoriteState() = _book.value.data?.let {
        booksRepository.changeFavoriteState(it)
    } ?: false

}