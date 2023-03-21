package com.waroudi.tapalibrary.ui.books

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.databinding.FragmentBooksBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
import com.waroudi.tapalibrary.utils.Constants
import com.waroudi.tapalibrary.utils.navigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksFragment : BaseFragment<FragmentBooksBinding>() {

    private val viewModel: BooksViewModel by viewModel()

    override fun setupView() {
    }

    override fun subscribesUI() {
        with(viewModel) {
            observeFlow(bookList,
                success = { handleBooks(it) })
            getAllBooks()
        }

    }

    private fun handleBooks(books: List<Book>) {
        val booksAdapter = BooksAdapter(books, ::onBookClicked)
        val manager = LinearLayoutManager(requireContext())
        binding.recyclerBooks.apply {
            layoutManager = manager
            adapter = booksAdapter
        }
    }

    private fun onBookClicked(book: Book) {
        val args = bundleOf(Constants.KEY_SELECTED_BOOK to book)
        navigate(R.id.action_books_to_details, args)
    }
}