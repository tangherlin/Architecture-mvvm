package com.example.myarchitecture.utils

import com.example.myarchitecture.domain.Discount
import com.example.myarchitecture.domain.Product
import com.example.myarchitecture.network.model.NetworkProduct

// A wrapper for handling failing requests
sealed class Result<T> {

    data class Success<T>(val value: T) : Result<T>()

    data class Failure<T>(val throwable: Throwable) : Result<T>()

}

// A cluster of DTOs to be mapped into a Product
data class DataProduct(
    val networkProduct: NetworkProduct,
    val isDiscounted: Boolean
)

data class DataProductDiscounted(
    val product : Product,
    val discount : Discount? = null
){

}