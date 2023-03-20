package com.waroudi.tapalibrary.ui.dialogs

import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.databinding.DialogErrorBinding
import com.waroudi.tapalibrary.ui.base.BaseDialogFragment

class ErrorDialog(
    private val message: String,
    private val buttonText: String? = null,
    private val action: (() -> Unit)? = null
) : BaseDialogFragment<DialogErrorBinding>() {

    override fun setupView() {
        binding.lblDescription.text = message
        buttonText?.let {
            binding.btnOkay.text = it
        }
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.bg_round_corner_dialog)
    }

    override fun setupListeners() {
        binding.btnOkay.setOnClickListener {
            action?.invoke()
            safeDismiss()
        }
    }
}