package com.cap.front.view.epoxy.view

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.widget.RelativeLayout
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.cap.front.R
import com.cap.front.databinding.ViewBannerBinding

@ModelView(
    autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT,
    baseModelClass = BaseView::class
)
class SplitBannerView : RelativeLayout {

    private lateinit var B: ViewBannerBinding

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
        val view = inflate(context, R.layout.view_banner, this)
        B = ViewBannerBinding.bind(view)
    }

    @ExperimentalCoilApi
    @ModelProp
    fun bannerUrl(url: String) {
        val displayMetrics = Resources.getSystem().displayMetrics
        val height = displayMetrics.heightPixels / 4.0
        val width = displayMetrics.widthPixels / 2.15

        val request = ImageRequest.Builder(context)
            .data(url)
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


        B.imgIcon.layoutParams.height = (height).toInt()
        B.imgIcon.layoutParams.width = (width).toInt()
        B.imgIcon.requestLayout()
    }

    @OnViewRecycled
    fun clear() {
    }
}