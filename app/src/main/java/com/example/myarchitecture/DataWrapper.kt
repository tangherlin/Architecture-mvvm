package com.example.myarchitecture

sealed class DataWrapper<out T>

data class Success<out T>(val data: T) : DataWrapper<T>()
class EmptySuccess<out T> : DataWrapper<T>()
data class Failure<out T>(val requestError: Exception) : DataWrapper<T>()
data class Loading<out T>(val loading: Boolean) : DataWrapper<T>()
