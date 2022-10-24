package com.example.myarchitecture.domain

import java.math.BigDecimal


data class TotalProductWithDiscount(val totalAmount : BigDecimal,val productWithDiscountList : List<ProductWithDiscount>)
data class ProductWithDiscount(
    val productId: String,
    val name: String,
    val price: BigDecimal,
    val discount: Discount? = null
)