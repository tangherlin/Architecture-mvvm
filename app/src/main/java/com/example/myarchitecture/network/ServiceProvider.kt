package com.example.myarchitecture.network

import android.content.Context

class ServiceProvider private constructor(private val context: Context) {

    class Builder(private val context: Context) {

        @Suppress("UNCHECKED_CAST")
        fun <S>createService(clazz: Class<S>): S {
            return when(clazz) {
                ProductService::class.java -> ProductService(ServiceProvider(context)) as S
                else -> throw IllegalArgumentException()
            }
        }
    }

    fun getAssets(filename: String): String =
        context.assets.open(filename)
            .bufferedReader()
            .use { it.readText() }
}