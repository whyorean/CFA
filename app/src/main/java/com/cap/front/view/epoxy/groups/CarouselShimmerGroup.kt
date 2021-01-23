package com.cap.front.view.epoxy.groups

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.cap.front.R
import com.cap.front.view.epoxy.CarouselHorizontalModel_
import com.cap.front.view.epoxy.view.HeaderViewShimmerModel_
import com.cap.front.view.epoxy.view.ProductViewShimmerModel_
import java.util.*
import kotlin.collections.ArrayList

class CarouselShimmerGroup() :
    EpoxyModelGroup(
        R.layout.model_carousel_group, buildModels()
    ) {
    companion object {
        private fun buildModels(): List<EpoxyModel<*>> {
            val models = ArrayList<EpoxyModel<*>>()
            val clusterViewModels = mutableListOf<EpoxyModel<*>>()
            val idPrefix = UUID.randomUUID()

            for (i in 1..8) {
                clusterViewModels.add(
                    ProductViewShimmerModel_()
                        .id(i)
                )
            }

            models.add(
                HeaderViewShimmerModel_()
                    .id("shimmer_header")
            )

            models.add(
                CarouselHorizontalModel_()
                    .id("cluster_${idPrefix}")
                    .models(clusterViewModels)
            )
            return models
        }
    }
}