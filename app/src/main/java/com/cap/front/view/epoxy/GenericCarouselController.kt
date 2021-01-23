package com.cap.front.view.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.cap.front.Content
import com.cap.front.Product
import com.cap.front.StreamBundle
import com.cap.front.view.epoxy.groups.CarouselShimmerGroup
import com.cap.front.view.epoxy.groups.ProductModelGroup
import com.cap.front.view.epoxy.groups.SplitBannerModelGroup
import com.cap.front.view.epoxy.view.BannerViewModel_

open class GenericCarouselController(private val callbacks: Callbacks) :
    TypedEpoxyController<StreamBundle?>() {

    interface Callbacks {
        fun onHeaderClicked(content: Content)
        fun onBundleScrolled(content: Content)
        fun onProductClick(product: Product)
        fun onProductLongClick(product: Product)
    }

    override fun buildModels(streamBundle: StreamBundle?) {
        setFilterDuplicates(true)
        if (streamBundle == null) {
            for (i in 1..3) {
                add(
                    CarouselShimmerGroup()
                        .id(i)
                )
            }
        } else {
            val contentList = streamBundle.content

            for (content in contentList) {
                when (content.sectionType) {
                    "horizontalFreeScroll" -> add(
                        ProductModelGroup(content, callbacks)
                    )
                    "splitBanner" -> add(
                        SplitBannerModelGroup(content, callbacks)
                    )
                    "banner" -> add(
                        BannerViewModel_()
                            .id("banner")
                            .bannerUrl(content.bannerImage)
                    )
                }
            }
        }
    }
}