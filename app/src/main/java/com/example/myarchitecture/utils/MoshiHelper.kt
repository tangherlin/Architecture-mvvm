package com.example.myarchitecture.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException
import java.util.*

object MoshiHelper {

    inline fun <reified D> getListObjectFromAssets(json: String? = null): List<D>? {
        return json.fromJsonStringToList() // D
    }

    inline fun <reified T> String?.fromJsonStringToList(): List<T>? {
        if (this == null) return null

        val type = Types.newParameterizedType(MutableList::class.java, T::class.java)
        val adapter = moshiKtBuilder().adapter<List<T>>(type)
        return try {
            adapter.fromJson(this)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun moshiKtBuilder(addDateAdapter: Boolean = false): Moshi {
        return Moshi.Builder()
            .apply {
                // Kotlin adapter
                add(KotlinJsonAdapterFactory())

                // Date adapter
                if (addDateAdapter) {
                    add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                }
            }
            .build()
    }
}