package com.waroudi.tapalibrary.utils

fun Any?.toSafeString(altText: String = "Unknown", checkBlank: Boolean = false): String {
    return this?.toString().takeIf { if (checkBlank) it.isNullOrBlank().not() else it.notNull() } ?: altText
}

fun Any?.notNull() = isNull().not()

fun Any?.isNull() = this == null