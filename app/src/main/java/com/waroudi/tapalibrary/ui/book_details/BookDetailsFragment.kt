package com.waroudi.tapalibrary.ui.book_details

import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.data.models.error.TapaError
import com.waroudi.tapalibrary.databinding.FragmentBookDetailsBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
import com.waroudi.tapalibrary.utils.*
import com.waroudi.tapalibrary.utils.extensions.getSafeParcelable
import com.waroudi.tapalibrary.utils.extensions.navigateBack
import com.waroudi.tapalibrary.utils.extensions.setGlideImage
import com.waroudi.tapalibrary.utils.extensions.toVisible
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookDetailsFragment : BaseFragment<FragmentBookDetailsBinding>() {

    private val viewModel: BookDetailsViewModel by viewModel()

    override fun handleArgs() {
        // Receive Book or book ID from previous fragment
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
        // setup favorite icon behavior
        binding.viewFavorite.apply {
            setOnClickListener {
                val newState = viewModel.changeFavoriteState()
                setFavorite(newState, true)
            }
        }

        setupCardBehavior()
    }

    private fun setupCardBehavior() {
        with(binding) {
            btnDetails.setOnClickListener {
                val isFront = cardBook.rotationY == 0f
                val inView = if (isFront) layoutBack else layoutFront
                val outView = if (isFront) layoutFront else layoutBack
                outView.animate().alpha(0f).setDuration(150).start()
                cardBook.animate().rotationY(if (isFront) 180f else 0f).setDuration(400).start()
                inView.animate().alpha(1f).setStartDelay(200).setDuration(150).start()
                btnDetails.text = if (isFront) "Hide Details" else "Show Details"
            }
        }
    }

    override fun subscribesUI() {
        observeFlow(viewModel.book,
            success = { handleBook(it) },
            error = { handleBookError(it) })
    }

    /**
     * Applies retrieved Book to UI
     * @param book retrieved book
     */
    private fun handleBook(book: Book) {
        with(binding) {
            groupElements.toVisible()
            tvTitle.text = book.title
            tvAuthor.text = book.author
            viewPrice.setPrice(book.getPrice())
            val isbnLabel = book.isbn
            tvIsbn.text = isbnLabel
            viewFavorite.setFavorite(viewModel.isBookFavorite())
            book.description?.let { tvDescription.text = it }
            imgCover.setGlideImage(book.getBookCoverUrl(), R.drawable.book_cover)
        }
    }

    /**
     * Handles book retrieval error
     * @param error book retrieval error
     */
    private fun handleBookError(error: TapaError? = null) {
        error?.let {
            showDialogError(it, forcePerformAction = true) { navigateBack() }
        }
    }
}