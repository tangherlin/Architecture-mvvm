package com.example.myarchitecture.mapper

import com.example.myarchitecture.domain.Discount
import com.example.myarchitecture.domain.Product
import com.example.myarchitecture.network.model.NetworkProduct
import com.example.myarchitecture.network.model.NetworkProductDiscount

class ProductMapper {

    fun mapInProductList(networkProductList: List<NetworkProduct>?): List<Product> {
        val list = arrayListOf<Product>()
        networkProductList?.forEach {
            if (it.name != null && it.productId != null && it.price != null) {
                list.add(Product(it.productId, it.name, it.price.toBigDecimal()))
            }
        }
        return list
    }

    fun mapInDiscountList(networkDiscountList: List<NetworkProductDiscount>?): List<Discount> {
        val list = arrayListOf<Discount>()
        networkDiscountList?.forEach {
            if (it.discountId != null && it.productId != null && it.discount != null && it.description != null) {
                list.add(Discount(it.discountId, it.productId, it.description, it.discount.toBigDecimal()))
            }
        }
        return list
    }
}