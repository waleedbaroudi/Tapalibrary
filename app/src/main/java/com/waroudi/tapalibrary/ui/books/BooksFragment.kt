package com.waroudi.tapalibrary.ui.books

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.databinding.FragmentBooksBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
import com.waroudi.tapalibrary.utils.Constants
import com.waroudi.tapalibrary.utils.extensions.navigate
import com.waroudi.tapalibrary.utils.extensions.navigateBack
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksFragment : BaseFragment<FragmentBooksBinding>(), BookItemListener {

    private val viewModel: BooksViewModel by viewModel()

    override fun setupView() {
    }

    override fun subscribesUI() {
        with(viewModel) {
            observeFlow(bookList,
                success = { handleBooks(it) },
                error = {
                    showDialogError(it, forcePerformAction = true) { navigateBack() }
                })
            getAllBooks()
        }

    }

    /**
     * Handles the retrieved list of books, sets up the book recycler view
     * @param books list of retrieved books
     */
    private fun handleBooks(books: List<Book>) {
        val favorites = viewModel.getFavoritesIds()
        val booksAdapter = BooksAdapter(books, favorites, this)
        val manager = GridLayoutManager(requireContext(), 2)
        binding.recyclerBooks.apply {
            layoutManager = manager
            adapter = booksAdapter
        }
    }

    override fun onBookClicked(book: Book) {
        val args = bundleOf(Constants.KEY_SELECTED_BOOK to book)
        navigate(R.id.action_books_to_details, args)
    }

    override fun onBookFavoriteClicked(book: Book, action: (newState: Boolean) -> Unit) {
        val newState = viewModel.changeFavoriteState(book)
        action(newState)
    }
}