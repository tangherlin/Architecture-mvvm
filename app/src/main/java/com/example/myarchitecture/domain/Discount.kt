package com.example.myarchitecture.domain

import java.math.BigDecimal

/*
[
  {
    "productId": "3",
    "discountId": "1",
    "description": "Google Discount",
    "discount": "100.00"
  },
  ...
  ]
 */
data class Discount(
    val discountId: String,
    val productId: String,
    val description: String,
    val discountAmount: BigDecimal
)