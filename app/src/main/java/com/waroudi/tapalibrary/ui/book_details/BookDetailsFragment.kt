package com.waroudi.tapalibrary.ui.book_details

import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import com.waroudi.tapalibrary.databinding.FragmentBookDetailsBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
import com.waroudi.tapalibrary.utils.*
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

    override fun setupListeners() {
        binding.viewFavorite.apply {
            setOnClickListener {
                val newState = viewModel.changeFavoriteState()
                setFavorite(newState, true)
            }
        }
    }

    override fun subscribesUI() {
        observeFlow(viewModel.book,
            success = { handleBook(it) },
            error = { handleBookError(it) })
    }

    private fun handleBook(book: Book) {
        with(binding) {
            groupElements.toVisible()
            tvTitle.text = book.title
            tvAuthor.text = book.author
            viewPrice.setPrice(book.getPrice())
            val isbnLabel = book.isbn
            tvIsbn.text = isbnLabel
            viewFavorite.setFavorite(viewModel.isBookFavorite())
            imgCover.setGlideImage(book.getBookCoverUrl(), R.drawable.book_cover)
        }
    }

    private fun handleBookError(error: TapaLibraryError? = null) {
        error?.let {
            showDialogError(it) { navigateBack() }
        }
    }
}