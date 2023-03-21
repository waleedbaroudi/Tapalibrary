package com.waroudi.tapalibrary.ui.book_details

import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import com.waroudi.tapalibrary.databinding.FragmentBookDetailsBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
import com.waroudi.tapalibrary.utils.Constants
import com.waroudi.tapalibrary.utils.getSafeParcelable
import com.waroudi.tapalibrary.utils.navigateBack
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookDetailsFragment : BaseFragment<FragmentBookDetailsBinding>() {

    private val viewModel: BookDetailsViewModel by viewModel()

    override fun handleArgs() {
        arguments?.let { args ->
            args.getSafeParcelable<Book>(Constants.KEY_SELECTED_BOOK)?.let {
                viewModel.setBook(it)
            } ?: args.getString(Constants.KEY_SELECTED_BOOK_ID)?.let {
                viewModel.getBookById(it)
            } ?: handleBookError()
        }

    }

    override fun setupView() {
    }

    override fun subscribesUI() {
        observeFlow(viewModel.book,
            success = { handleBook(it) },
            error = { handleBookError(it) })
    }

    private fun handleBook(book: Book) {
        with(binding) {
            tvTitle.text = book.title
            tvAuthor.text = book.author
            tvPrice.text = book.getFormattedPrice()
            val isbnLabel = "isbn: ${book.isbn}"
            tvIsbn.text = isbnLabel
        }
    }

    private fun handleBookError(error: TapaLibraryError? = null) {
        error?.let {
            showDialogError(it) { navigateBack() }
        }
    }
}