package com.waroudi.tapalibrary.ui.base

import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.waroudi.tapalibrary.R
import com.waroudi.tapalibrary.utils.createBindingInstance

abstract class BaseDialogFragment<VB: ViewBinding>: DialogFragment() {
    protected lateinit var binding: VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = createBindingInstance(inflater, container)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.bg_round_corner_dialog)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
    }

    private fun adjustDialog() { // TODO: do it better
        if (dialog == null) {
            return
        }
        val window = dialog!!.window
        val size = Point()
        if (window == null || window.windowManager == null) {
            return
        }
        // Store dimensions of the screen in `size`

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = activity?.display
            display?.getRealSize(size)
        } else {
            @Suppress("DEPRECATION")
            val display = activity?.windowManager?.defaultDisplay
            @Suppress("DEPRECATION")
            display?.getSize(size)
        }
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((size.x * 0.9).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
    }

    override fun onResume() {
        super.onResume()
        adjustDialog()
    }

    fun show(manager: FragmentManager) {
        if (isVisible) return
        try {
            super.show(manager, this::class.java.canonicalName)
        } catch (e: Exception) {
            // TODO: report to crashlytics
        }
    }

    fun safeDismiss() {
        if (isVisible.not()) return
        dismissAllowingStateLoss()
    }

    abstract fun setupView()
    abstract fun setupListeners()
}