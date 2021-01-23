package com.cap.front

data class Content(
    var sectionType: String,
    var name: String,
    var products: List<Product>?,
    var bannerImage: String,
    var firstImage: String,
    var secondImage: String,
)