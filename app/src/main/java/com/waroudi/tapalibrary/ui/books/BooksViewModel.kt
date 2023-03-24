package com.waroudi.tapalibrary.ui.books

import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.models.state.UiModelState
import com.waroudi.tapalibrary.data.repositories.BooksRepository
import com.waroudi.tapalibrary.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BooksViewModel(private val repository: BooksRepository) : BaseViewModel() {

    private val _bookList = MutableStateFlow<UiModelState<List<Book>>>(UiModelState.None)
    val bookList: StateFlow<UiModelState<List<Book>>> get() = _bookList

    fun getAllBooks() {
        flowWrapper(repository.getAllBooks(), _bookList, true)
    }
    
    fun getFavoritesIds() = repository.getFavoritesIds()
    
    fun changeFavoriteState(book:Book) = repository.changeFavoriteState(book)
}