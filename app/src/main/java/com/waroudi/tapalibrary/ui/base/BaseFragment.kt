package com.waroudi.tapalibrary.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
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

    private fun <VB : ViewBinding> Fragment.createBindingInstance(
        inflater: LayoutInflater,
        container: ViewGroup? = null
    ): VB {
        val vbType = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val vbClass = vbType as Class<VB>
        val method = vbClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )

        // Call VB.inflate(inflater, container, false) Java static method
        return method.invoke(null, inflater, container, false) as VB
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
}