package com.example.myarchitecture.repository

import com.example.myarchitecture.domain.Discount
import com.example.myarchitecture.domain.Product
import com.example.myarchitecture.domain.ProductWithDiscount
import com.example.myarchitecture.domain.TotalProductWithDiscount


interface ProductRepository {
    suspend fun getProductList(): List<Product>
    suspend fun getDiscountList(): List<Discount>
    suspend fun getProductWithDiscount() : TotalProductWithDiscount
}
