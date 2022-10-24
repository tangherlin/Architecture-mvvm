package com.example.myarchitecture.repository

import androidx.lifecycle.MutableLiveData
import com.example.myarchitecture.domain.Discount
import com.example.myarchitecture.domain.Product
import com.example.myarchitecture.domain.ProductWithDiscount
import com.example.myarchitecture.domain.TotalProductWithDiscount
import com.example.myarchitecture.mapper.ProductMapper
import com.example.myarchitecture.network.ProductService
import com.example.myarchitecture.utils.ProductHelper
import kotlinx.coroutines.*
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    // private val productDataMapper: Mapper<DataProduct, Product>,
    var provider: ProductService
) : ProductRepository {


    val product: MutableLiveData<List<ProductWithDiscount>> = MutableLiveData<List<ProductWithDiscount>>()

    override suspend fun getProductList(): List<Product> {
        return ProductMapper().mapInProductList(provider.fetchProductList())
    }

    override suspend fun getDiscountList(): List<Discount> {
        return ProductMapper().mapInDiscountList(provider.fetchDiscountList())
    }

    override suspend fun getProductWithDiscount(): TotalProductWithDiscount {
        val productList = getProductList()
        val discountList = getDiscountList()
        val productWithDiscountList = ProductHelper.combineProductWithDiscount(productList, discountList)
        val totalAmount = ProductHelper.getTotalAmount(productWithDiscountList)
        return TotalProductWithDiscount(totalAmount, productWithDiscountList)
    }
}

