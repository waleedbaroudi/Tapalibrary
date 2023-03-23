package com.waroudi.tapalibrary.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.data.models.api.Book
import com.waroudi.tapalibrary.databinding.LayoutBookCellBinding
import com.waroudi.tapalibrary.utils.setGlideImage

class BooksAdapter(
    private val books: List<Book>,
    private val onBookClickedListener: (Book) -> Unit
) : Adapter<BooksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutBookCellBinding.inflate(inflater, parent, false)
        return BooksViewHolder(binding, onBookClickedListener)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount() = books.size
}

class BooksViewHolder(
    private val binding: LayoutBookCellBinding,
    private val onBookClickedListener: (Book) -> Unit
) : ViewHolder(binding.root) {
    fun bind(book: Book) {
        with(binding) {
            tvTitle.text = book.title
//            tvAuthor.text = book.author
            viewPrice.setPrice(book.getPrice())
            imgThumbnail.setGlideImage(book.getBookCoverUrl(), R.drawable.book_cover)
            root.setOnClickListener { onBookClickedListener(book) }
        }
    }
}