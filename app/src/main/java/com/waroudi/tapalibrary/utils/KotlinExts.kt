package com.waroudi.tapalibrary.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

fun Any?.toSafeString(altText: String = "Unknown", checkBlank: Boolean = false): String {
    return this?.toString().takeIf { if (checkBlank) it.isNullOrBlank().not() else it.notNull() } ?: altText
}

fun Any?.notNull() = isNull().not()

fun Any?.isNull() = this == null

// Bundle
inline fun <reified T : Parcelable?> Bundle.getSafeParcelable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable<T>(key)
    }
}