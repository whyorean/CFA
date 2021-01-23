package com.cap.front.view.epoxy.view

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.ModelView
import com.facebook.shimmer.ShimmerFrameLayout
import com.cap.front.R
import com.cap.front.databinding.ViewProductShimmerBinding

@ModelView(
    autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT,
    baseModelClass = BaseView::class
)
class ProductViewShimmer : ShimmerFrameLayout {

    private lateinit var B: ViewProductShimmerBinding

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        val view = inflate(context, R.layout.view_product_shimmer, this)
        B = ViewProductShimmerBinding.bind(view)
    }
}