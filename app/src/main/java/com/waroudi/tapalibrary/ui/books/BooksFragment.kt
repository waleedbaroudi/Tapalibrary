package com.waroudi.tapalibrary.ui.books

import com.waroudi.tapalibrary.databinding.FragmentBooksBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksFragment : BaseFragment<FragmentBooksBinding>() {

    private val viewModel: BooksViewModel by viewModel()

    override fun setupView() {
        binding.tvTest.text = viewModel.test
    }
}