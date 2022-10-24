package com.example.myarchitecture.utils

import com.example.myarchitecture.domain.Discount
import com.example.myarchitecture.domain.Product
import com.example.myarchitecture.domain.ProductWithDiscount
import java.math.BigDecimal

object ProductHelper {

    fun combineProductWithDiscount(productList: List<Product>, discountList: List<Discount>): List<ProductWithDiscount> {
        val list = arrayListOf<ProductWithDiscount>()
        productList.forEach { product ->
            val discountItemList = discountList.filter { it.productId == product.productId }
            list.add(
                if (discountItemList.isEmpty()) {
                    ProductWithDiscount(product.productId, product.name, product.price, null)
                } else {
                    val discount = discountItemList.first()
                    ProductWithDiscount(
                        product.productId,
                        product.name,
                        product.price,
                        discount
                    )
                }
            )
        }
        return list
    }


    fun getTotalAmount(productWithDiscountList: List<ProductWithDiscount>): BigDecimal {
        val priceList = productWithDiscountList.map {
            if (it.discount != null) {
                it.price.minus(it.discount.discountAmount)
            } else {
                it.price
            }
        }
        val totatAmount = priceList.sumOf { it }

        return totatAmount
    }
}