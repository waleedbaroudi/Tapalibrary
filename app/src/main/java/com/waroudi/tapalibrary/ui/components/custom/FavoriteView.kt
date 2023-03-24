package com.waroudi.tapalibrary.ui.components.custom

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.waroudi.tapalibrary.databinding.ViewFavoriteBinding

class FavoriteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding = ViewFavoriteBinding.inflate(LayoutInflater.from(context), this, true)

    fun isFavoriteEnabled() = binding.animFavorite.frame in 29..56


    init {
        binding.animFavorite.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                binding.root.isEnabled = false
            }

            override fun onAnimationEnd(animator: Animator) {
                binding.root.isEnabled = true
            }

            override fun onAnimationCancel(a: Animator) {}

            override fun onAnimationRepeat(a: Animator) {}

        })
    }

    fun setFavorite(enabled: Boolean, animate: Boolean = false) {
        binding.animFavorite.apply {
            if (animate.not()) {
                progress = if (enabled) 0.5f else 0f
            } else {
                if (enabled) setMinAndMaxFrame(7, 30) else setMinAndMaxFrame(55, 76)
                playAnimation()
            }

        }

    }
}