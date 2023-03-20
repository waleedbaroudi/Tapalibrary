package com.waroudi.tapalibrary.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

fun Fragment.navigate(@IdRes actionId:  Int, args: Bundle? = null) {
    findNavController().navigate(actionId, args)
}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}

fun <VB : ViewBinding> Fragment.createBindingInstance(
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