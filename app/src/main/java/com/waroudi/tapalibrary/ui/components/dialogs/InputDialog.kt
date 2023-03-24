package com.waroudi.tapalibrary.ui.components.dialogs

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.waroudi.tapalibrary.databinding.DialogInputBinding
import com.waroudi.tapalibrary.ui.base.BaseDialogFragment
import com.waroudi.tapalibrary.utils.hideSoftKeyboard
import com.waroudi.tapalibrary.utils.toGone
import com.waroudi.tapalibrary.utils.toVisible

/**
 * Custom dialog for taking user input
 * @param title the input title
 * @param buttonTitle the label of the button
 * @param inputHint the hint shown on the input field
 * @param icon an optional icon resource
 * @param buttonAction the action to be performed with the given input
 */
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

    override fun setupListeners() { // TODO: disable button when field empty
        with(binding) {
            btnOkay.setOnClickListener {
                root.hideSoftKeyboard()
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