package com.waroudi.tapalibrary.di

import com.waroudi.tapalibrary.data.network.api.BookApi
import com.waroudi.tapalibrary.data.network.services.BooksService
import com.waroudi.tapalibrary.data.repositories.BooksRepository
import com.waroudi.tapalibrary.ui.books.BooksViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val url = "http://tpbookserver.herokuapp.com/" // TODO: move to constants

val appModule = module {

    // API
    factory {
        val client = OkHttpClient.Builder()
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()

        Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(BookApi::class.java) }

    // Services
    single { BooksService(get()) }

    // Repositories
    single { BooksRepository(get()) }

    // ViewModels
    viewModel { BooksViewModel(get()) }
}
