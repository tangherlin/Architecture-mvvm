package com.example.myarchitecture.domain

import java.math.BigDecimal

/*
  "productId": "3",
    "discountId": "1",
    "description": "Google Discount",
    "discount": "100.00"
 */
data class Product(
    val productId: String,
    val name: String,
    val price: BigDecimal
)
