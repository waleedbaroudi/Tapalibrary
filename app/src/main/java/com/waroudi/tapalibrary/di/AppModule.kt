package com.waroudi.tapalibrary.di

import com.waroudi.tapalibrary.ui.books.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModels
    viewModel { BooksViewModel() }
}
