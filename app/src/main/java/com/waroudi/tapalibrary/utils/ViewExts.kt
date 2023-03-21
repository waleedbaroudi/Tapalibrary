package com.waroudi.tapalibrary.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.waroudi.tapalibrary.R

fun ImageView.setGlideImage(url: String?, @DrawableRes placeholderResId: Int? = null) {
    val loadingPlaceholder = getDefaultPlaceHolder(context)

    val builder = Glide.with(context).load(url)
    placeholderResId?.let { builder.error(it) }
    builder.transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(loadingPlaceholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

private fun getDefaultPlaceHolder(context: Context) = CircularProgressDrawable(context).apply {
    strokeWidth = 4f
    centerRadius = 13f
    arrowEnabled = true
    setColorSchemeColors(R.color.primary)
    start()
}