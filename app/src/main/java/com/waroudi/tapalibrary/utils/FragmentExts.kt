package com.waroudi.tapalibrary.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(@IdRes actionId:  Int, args: Bundle? = null) {
    findNavController().navigate(actionId, args)
}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}