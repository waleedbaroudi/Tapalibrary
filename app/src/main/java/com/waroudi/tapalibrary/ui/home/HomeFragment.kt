package com.waroudi.tapalibrary.ui.home

import com.waroudi.tapalibrary.databinding.FragmentHomeBinding
import com.waroudi.tapalibrary.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun setupView() {
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.btnBookList.setOnClickListener {
            // TODO: navigate to book list screen
        }
    }

}