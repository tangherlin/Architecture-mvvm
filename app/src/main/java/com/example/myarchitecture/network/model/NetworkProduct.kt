package com.example.myarchitecture.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkProduct(
    @Json(name = "productId")
    val productId: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "price")
    val price: String? = null
)