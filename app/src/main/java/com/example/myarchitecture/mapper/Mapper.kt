package com.example.myarchitecture.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}