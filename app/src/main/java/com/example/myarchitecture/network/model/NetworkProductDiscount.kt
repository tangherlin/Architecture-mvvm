package com.example.myarchitecture.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkProductDiscount(
    @Json(name = "productId")
    val productId: String?= null,
    @Json(name = "discountId")
    val discountId: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "discount")
    val discount: String? = null
)