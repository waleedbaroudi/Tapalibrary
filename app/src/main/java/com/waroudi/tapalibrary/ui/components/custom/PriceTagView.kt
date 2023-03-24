package com.waroudi.tapalibrary.ui.components.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.waroudi.tapalibrary.data.models.api.Price
import com.waroudi.tapalibrary.databinding.ViewPriceTagBinding

/**
 * Custom view for denoting book price and currency
 */
class PriceTagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding =
        ViewPriceTagBinding.inflate(LayoutInflater.from(context), this, true)

    /**
     * Updates the view with a [Price]
     * @param price the given price
     */
    fun setPrice(price: Price) {
        with(binding) {
            tvAmount.text = price.getFormattedAmount()
            tvCurrency.text = price.getCurrencySymbol()
        }
    }
}