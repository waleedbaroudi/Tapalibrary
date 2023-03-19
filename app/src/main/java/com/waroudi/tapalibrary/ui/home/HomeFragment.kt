package com.waroudi.tapalibrary.ui.home

import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.databinding.FragmentHomeBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment
import com.waroudi.tapalibrary.utils.navigate

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun setupView() {
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnBookList.setOnClickListener {
            navigate(R.id.action_home_to_books)
        }
    }

}