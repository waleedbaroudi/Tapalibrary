package com.waroudi.tapalibrary.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import com.waroudi.tapalibrary.databinding.ActivityMainBinding
import com.waroudi.tapalibrary.databinding.LayoutProgressDialogBinding
import com.waroudi.tapalibrary.ui.dialogs.ErrorDialog
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    private var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.progressCounter.observe(this) {
            if (it == 0) hideProgress()
            else showProgress()
        }
    }

    fun updateProgress(show: Boolean) {
        viewModel.updateProgress(show)
    }

    private fun isProgressShowing(): Boolean = progressDialog?.isShowing == true

    private fun showProgress() {
        if (isProgressShowing().not()) {
            if (progressDialog == null) progressDialog = createProgressLoading()
            progressDialog?.show()
        }
    }

    private fun hideProgress() = progressDialog?.dismiss()

    private fun createProgressLoading(): AlertDialog {
        val binding = LayoutProgressDialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(binding.root)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun showDialogError(
        exception: TapaLibraryError,
        btnText: String?,
        action: (() -> Unit)?
    ) {
        ErrorDialog(
            message = exception.safeMessage(),
            buttonText = btnText,
            action = action
        ).show(supportFragmentManager)
    }
}