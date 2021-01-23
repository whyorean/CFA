package com.cap.front.view.epoxy.groups

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.cap.front.Content
import com.cap.front.R
import com.cap.front.util.Log
import com.cap.front.view.epoxy.CarouselHorizontalModel_
import com.cap.front.view.epoxy.GenericCarouselController
import com.cap.front.view.epoxy.view.HeaderViewModel_
import com.cap.front.view.epoxy.view.ProductViewModel_

class ProductModelGroup(
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

            models.add(
                HeaderViewModel_()
                    .id("${idPrefix}_header")
                    .title(content.name)
                    .browseUrl("")
                    .click { _ ->
                        callbacks.onHeaderClicked(content)
                    }
            )

            for (product in content.products!!) {
                clusterViewModels.add(
                    ProductViewModel_()
                        .id(product.name)
                        .product(product)
                        .click { _ ->
                            //callbacks.onProductClick(it)
                        }
                        .longClick { _ ->
                            // callbacks.onProductLongClick(it)
                            false
                        }
                        .onBind { _, _, position ->
                            val itemCount = clusterViewModels.count()
                            if (itemCount >= 2) {
                                if (position == clusterViewModels.count() - 2) {
                                    callbacks.onBundleScrolled(content)
                                    Log.i("Bundle %s Scrolled", content.name)
                                }
                            }
                        }
                )
            }

            models.add(
                CarouselHorizontalModel_()
                    .id("cluster")
                    .models(clusterViewModels)
            )

            return models
        }
    }
}