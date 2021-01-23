package com.cap.front.view.epoxy.view

import android.content.Context
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
    autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,
    baseModelClass = BaseView::class
)
class BannerView : RelativeLayout {

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
    }

    @OnViewRecycled
    fun clear() {
    }
}