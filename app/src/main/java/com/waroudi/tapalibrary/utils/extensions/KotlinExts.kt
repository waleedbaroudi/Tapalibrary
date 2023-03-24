package com.waroudi.tapalibrary.utils.extensions

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

/**
 * Returns a safe string representation of this object
 * @param altText returned text if this object is null
 * @param checkBlank a flag to return [altText] if the String representation of this object is blank
 * @return String representation of the object
 */
fun Any?.toSafeString(altText: String = "Unknown", checkBlank: Boolean = false): String {
    return this?.toString().takeIf { if (checkBlank) it.isNullOrBlank().not() else it.notNull() } ?: altText
}

fun Any?.notNull() = isNull().not()

fun Any?.isNull() = this == null

// Bundle
/**
 * OS version compatible function to get [Parcelable] from a [Bundle]
 * @param key the key of the requested parcelable
 * @return the parcelable if it exists, null otherwise
 */
inline fun <reified T : Parcelable?> Bundle.getSafeParcelable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable<T>(key)
    }
}