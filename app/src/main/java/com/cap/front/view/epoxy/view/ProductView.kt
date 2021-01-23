package com.cap.front.view.epoxy.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.cap.front.Product
import com.cap.front.R
import com.cap.front.databinding.ViewProductBinding

@ModelView(
    autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT,
    baseModelClass = BaseView::class
)
class ProductView : RelativeLayout {

    private lateinit var B: ViewProductBinding

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
        val view = inflate(context, R.layout.view_product, this)
        B = ViewProductBinding.bind(view)
    }

    @ExperimentalCoilApi
    @ModelProp
    fun product(product: Product) {
        B.txtLine1.text = product.name
        B.txtLine2.text = product.price
        B.txtLine3.text = product.type

        val request = ImageRequest.Builder(context)
            .data(product.imageURL)
            .target(
                onStart = { _ ->
                    B.imgIcon.load(R.drawable.bg_placeholder)
                },
                onSuccess = { result ->
                    B.imgIcon.load(result)
                },
                onError = { _ ->
                    B.imgIcon.load(R.drawable.bg_placeholder)
                }
            )
            .build()
        context.imageLoader.enqueue(request)
    }

    @CallbackProp
    fun click(onClickListener: OnClickListener?) {
        B.root.setOnClickListener(onClickListener)
    }

    @CallbackProp
    fun longClick(onClickListener: OnLongClickListener?) {
        B.root.setOnLongClickListener(onClickListener)
    }

    @OnViewRecycled
    fun clear() {
    }
}