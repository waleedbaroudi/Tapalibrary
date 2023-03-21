package com.waroudi.tapalibrary.ui.dialogs

import androidx.annotation.IdRes
import com.waroudi.tapalibrary.databinding.DialogInputBinding
import com.waroudi.tapalibrary.ui.base.BaseDialogFragment

class InputDialog
    (
    private val title: String,
    private val buttonTitle: String,
    private val inputHint: String,
    @IdRes private val icon: Int? = null,
    private val buttonAction: (input: String) -> Unit
) : BaseDialogFragment<DialogInputBinding>() {

    override fun setupView() {
        with(binding) {
            tvTitle.text = title
            icon?.let { imgIcon.setImageResource(it) }
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