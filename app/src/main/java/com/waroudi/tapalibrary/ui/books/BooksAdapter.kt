package com.waroudi.tapalibrary.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.databinding.LayoutBookCellBinding
import com.waroudi.tapalibrary.utils.extensions.setGlideImage

class BooksAdapter(
    private val books: List<Book>,
    private val favorites: List<String>,
    private val bookItemListener: BookItemListener
) : Adapter<BooksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutBookCellBinding.inflate(inflater, parent, false)
        return BooksViewHolder(binding, bookItemListener)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = books[position]
        val isFavorite = book.id.toString() in favorites
        holder.bind(books[position], isFavorite)
    }

    override fun getItemCount() = books.size
}

class BooksViewHolder(
    private val binding: LayoutBookCellBinding,
    private val bookItemListener: BookItemListener
) : ViewHolder(binding.root) {
    fun bind(book: Book, isFavorite: Boolean) {
        with(binding) {
            tvTitle.text = book.title
            viewPrice.setPrice(book.getPrice())
            imgThumbnail.setGlideImage(book.getBookCoverUrl(), R.drawable.book_cover)
            viewFavorite.setFavorite(isFavorite)
            viewFavorite.setOnClickListener { bookItemListener.onBookFavoriteClicked(book) { newState ->
                viewFavorite.setFavorite(newState, true)
            } }
            root.setOnClickListener { bookItemListener.onBookClicked(book) }
        }
    }
}

interface BookItemListener {
    fun onBookClicked(book: Book)
    fun onBookFavoriteClicked(book: Book, action: (newState: Boolean) -> Unit)
}