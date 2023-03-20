package com.waroudi.tapalibrary.ui.books

import androidx.recyclerview.widget.LinearLayoutManager
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.databinding.FragmentBooksBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
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
        val booksAdapter = BooksAdapter(books)
        val manager = LinearLayoutManager(requireContext())
        binding.recyclerBooks.apply {
            layoutManager = manager
            adapter = booksAdapter
        }
    }
}