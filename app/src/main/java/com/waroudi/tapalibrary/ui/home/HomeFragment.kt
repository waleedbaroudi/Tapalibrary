package com.waroudi.tapalibrary.ui.home

import androidx.core.os.bundleOf
import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.databinding.FragmentHomeBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
import com.waroudi.tapalibrary.ui.dialogs.InputDialog
import com.waroudi.tapalibrary.utils.Constants
import com.waroudi.tapalibrary.utils.navigate

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var bookSearchDialog: InputDialog? = null

    override fun setupView() {
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnBookList.setOnClickListener {
            navigate(R.id.action_home_to_books)
        }
        binding.btnBookSearch.setOnClickListener {
            showBookSearchDialog()
        }
    }

    private fun showBookSearchDialog() {
        if (bookSearchDialog == null)
            bookSearchDialog = InputDialog("Book Search", "Search", "Book ID", R.drawable.ic_search) { inputID ->
                navigate(R.id.action_home_to_details, bundleOf(Constants.KEY_SELECTED_BOOK_ID to inputID))
            }

        bookSearchDialog?.show(childFragmentManager)
    }

}