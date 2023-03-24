package com.waroudi.tapalibrary.ui.components.custom

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.waroudi.tapalibrary.databinding.ViewFavoriteBinding

/**
 * Custom view for denoting book favorite state
 */
class FavoriteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding = ViewFavoriteBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.animFavorite.addAnimatorListener(object : AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                // Disable interaction when animation starts
                binding.root.isEnabled = false
            }

            override fun onAnimationEnd(animator: Animator) {
                // Enable interaction when animation ends
                binding.root.isEnabled = true
            }

            override fun onAnimationCancel(a: Animator) {}

            override fun onAnimationRepeat(a: Animator) {}

        })
    }

    /**
     * Sets the state of the view (i.e. favorite or not favorite)
     * @param enabled the state to be set
     * @param animate whether to animate to the given state or just set it
     */
    fun setFavorite(enabled: Boolean, animate: Boolean = false) {
        binding.animFavorite.apply {
            if (animate.not()) {
                progress = if (enabled) 0.5f else 0f
            } else {
                // These frame values are specific to the set animation file
                if (enabled) setMinAndMaxFrame(7, 30) else setMinAndMaxFrame(55, 76)
                playAnimation()
            }

        }

    }
}