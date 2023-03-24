package com.waroudi.tapalibrary.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.waroudi.tapalibrary.data.local.TapaLocalStorage
import com.waroudi.tapalibrary.data.network.api.BookApi
import com.waroudi.tapalibrary.data.network.services.BooksService
import com.waroudi.tapalibrary.data.repositories.BooksRepository
import com.waroudi.tapalibrary.ui.book_details.BookDetailsViewModel
import com.waroudi.tapalibrary.ui.books.BooksViewModel
import com.waroudi.tapalibrary.ui.main.MainViewModel
import com.waroudi.tapalibrary.utils.Constants
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tapalibrary_store")

/**
 * Koin module containing all app dependencies
 */
val appModule = module {

    // API
    factory {
        // Set timeout configurations
        val client = OkHttpClient.Builder()
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()

        Retrofit.Builder()
            .baseUrl(Constants.BACKEND_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(BookApi::class.java) }

    // Services
    single { BooksService(get()) }

    // Local Storages
    single { TapaLocalStorage(androidApplication().dataStore) }

    // Repositories
    single { BooksRepository(get(), get()) }

    // ViewModels
    viewModel { MainViewModel() }
    viewModel { BooksViewModel(get()) }
    viewModel { BookDetailsViewModel(get()) }
}
