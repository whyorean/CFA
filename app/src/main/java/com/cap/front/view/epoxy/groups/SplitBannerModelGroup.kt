package com.cap.front.view.epoxy.groups

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.cap.front.R
import com.cap.front.Content
import com.cap.front.view.epoxy.CarouselHorizontalModel_
import com.cap.front.view.epoxy.GenericCarouselController
import com.cap.front.view.epoxy.view.SplitBannerViewModel_

class SplitBannerModelGroup(
    content: Content,
    callbacks: GenericCarouselController.Callbacks
) :
    EpoxyModelGroup(
        R.layout.model_carousel_group, buildModels(
            content,
            callbacks
        )
    ) {
    companion object {
        private fun buildModels(
            content: Content,
            callbacks: GenericCarouselController.Callbacks
        ): List<EpoxyModel<*>> {
            val models = mutableListOf<EpoxyModel<*>>()
            val clusterViewModels = mutableListOf<EpoxyModel<*>>()


            val idPrefix = content.name
            clusterViewModels.add(
                SplitBannerViewModel_()
                    .id(idPrefix, 1)
                    .bannerUrl(content.firstImage)
            )

            clusterViewModels.add(
                SplitBannerViewModel_()
                    .id(idPrefix, 2)
                    .bannerUrl(content.secondImage)
            )

            models.add(
                CarouselHorizontalModel_()
                    .id("split_banner")
                    .models(clusterViewModels)
            )
            return models
        }
    }
}