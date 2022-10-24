package com.example.myarchitecture.network

import com.example.myarchitecture.network.model.NetworkProduct
import com.example.myarchitecture.network.model.NetworkProductDiscount
import com.example.myarchitecture.utils.MoshiHelper
import kotlinx.coroutines.delay

class ProductService(private val provider: ServiceProvider) {


     suspend fun fetchProductList(): List<NetworkProduct>? {
        val json = getProductList()
        return MoshiHelper.getListObjectFromAssets(json)
    }

    suspend fun fetchDiscountList(): List<NetworkProductDiscount>? {
        val json = getSpecialOfferList()
        return MoshiHelper.getListObjectFromAssets(json)
    }

    private suspend fun getProductList(): String {
        delay(1000)
        return provider.getAssets("product_list.json")
    }

    private suspend fun getSpecialOfferList(): String {
        delay(2000)
        return provider.getAssets("special_offer_list.json")
    }
}
