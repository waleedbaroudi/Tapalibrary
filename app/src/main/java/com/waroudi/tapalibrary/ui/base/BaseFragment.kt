package com.waroudi.tapalibrary.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.waroudi.tapalibrary.data.models.error.TapaLibraryError
import com.waroudi.tapalibrary.data.models.state.UiModelState
import com.waroudi.tapalibrary.ui.main.MainActivity
import com.waroudi.tapalibrary.utils.createBindingInstance
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    protected lateinit var binding: VB
    private var _binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        if (_binding == null) {
            _binding = createBindingInstance(inflater, container)
            binding = _binding!!
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleArgs()
        setupView()
        setupListeners()
        subscribesUI()
    }

    abstract fun setupView()
    open fun handleArgs() {}
    open fun setupListeners() {}
    open fun subscribesUI() {} // used to observe ViewModels UiState members

    fun <T> observeFlow(
        stateFlow: StateFlow<UiModelState<T>>,
        loading: (() -> Unit)? = null,
        success: ((T) -> Unit)? = null,
        error: ((TapaLibraryError) -> Unit)? = null,
        end: (() -> Unit)? = null,
        ignoreLoading: Boolean = false,
        ignoreError: Boolean = false,
        cancelOnEnd: Boolean = false,
        hideLoadingOnEnd: Boolean = true
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            val hideLoading = hideLoadingOnEnd && !ignoreLoading
            stateFlow.collect { result ->
                when (result) {
                    is UiModelState.Loading -> {
                        loading?.invoke()
                        if (loading == null && !ignoreLoading) showProgress(true)
                    }
                    is UiModelState.Success<T> -> {
                        success?.invoke(result.data)
                        end?.invoke()
                        if (hideLoading) showProgress(false)
                        if (cancelOnEnd) cancel()
                    }

                    is UiModelState.Error -> {
                        val exc = result.exception
                        error?.invoke(exc)
                        end?.invoke()
                        if (error == null && !ignoreError) showDialogError(exc)
                        if (ignoreLoading.not()) showProgress(false)
                        if (cancelOnEnd) cancel()
                    }

                    is UiModelState.None -> {
                        if (hideLoading) showProgress(false)
                    }
                }
            }
        }
    }

    private fun showProgress(state: Boolean) {
        if (isAdded) requireMainActivity().updateProgress(state)
    }

    private fun showDialogError(
        exception: TapaLibraryError = TapaLibraryError.UnknownError,
        buttonText: String? = null,
        action: (() -> Unit)? = null
    ) {
        if (isAdded) requireMainActivity().showDialogError(
            exception = exception,
            btnText = buttonText,
            action = action
        )
    }

    private fun requireMainActivity() = (requireActivity() as MainActivity)

}