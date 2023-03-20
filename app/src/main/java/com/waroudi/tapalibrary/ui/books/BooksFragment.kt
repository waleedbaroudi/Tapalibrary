package com.waroudi.tapalibrary.ui.books

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
            success = {binding.tvTest.text = "book count: ${it.size}"})
            getAllBooks()
        }

    }
}