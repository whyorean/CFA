package com.cap.front.view.epoxy.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.annotation.Nullable
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.cap.front.R
import com.cap.front.databinding.ViewHeaderBinding


@ModelView(
    autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT,
    baseModelClass = BaseView::class
)
class HeaderView : RelativeLayout {

    private lateinit var B: ViewHeaderBinding

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
        val view = inflate(context, R.layout.view_header, this)
        B = ViewHeaderBinding.bind(view)
    }

    @ModelProp
    fun title(title: String?) {
        B.txtTitle.text = title
    }

    @JvmOverloads
    @ModelProp
    fun browseUrl(@Nullable browseUrl: String = String()) {
        if (browseUrl.isEmpty())
            B.imgAction.visibility = INVISIBLE
    }

    @CallbackProp
    fun click(onClickListener: OnClickListener?) {
        B.imgAction.setOnClickListener(onClickListener)
    }

    @OnViewRecycled
    fun clear() {
        B.imgAction.visibility = VISIBLE
    }
}