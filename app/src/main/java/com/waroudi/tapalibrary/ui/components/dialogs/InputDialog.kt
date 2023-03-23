package com.waroudi.tapalibrary.ui.components.dialogs

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.waroudi.tapalibrary.databinding.DialogInputBinding
import com.waroudi.tapalibrary.ui.base.BaseDialogFragment
import com.waroudi.tapalibrary.utils.toGone
import com.waroudi.tapalibrary.utils.toVisible

class InputDialog
    (
    private val title: String,
    private val buttonTitle: String,
    private val inputHint: String,
    @DrawableRes private val icon: Int? = null,
    private val buttonAction: (input: String) -> Unit
) : BaseDialogFragment<DialogInputBinding>() {

    override fun setupView() {
        with(binding) {
            tvTitle.text = title
            icon?.let {
                imgIcon.setImageResource(it)
                imgIcon.toVisible()
            } ?: imgIcon.toGone()
            btnOkay.text = buttonTitle
            editInput.hint = inputHint
        }
    }

    override fun setupListeners() {
        with(binding) {
            btnOkay.setOnClickListener {
                val input = editInput.text.toString()
                buttonAction(input)
                safeDismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.editInput.setText("")
    }
}