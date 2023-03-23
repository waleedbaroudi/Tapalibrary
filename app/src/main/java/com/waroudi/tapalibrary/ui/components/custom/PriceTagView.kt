package com.waroudi.tapalibrary.ui.components.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.waroudi.tapalibrary.data.models.api.Price
import com.waroudi.tapalibrary.databinding.ViewPriceTagBinding

class PriceTagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding =
        ViewPriceTagBinding.inflate(LayoutInflater.from(context), this, true)

    fun setPrice(price: Price) {
        with(binding) {
            tvAmount.text = price.getFormattedAmount()
            tvCurrency.text = price.getCurrencySymbol()
        }
    }
}