package com.example.myarchitecture.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

object CurrencyFormater {

    fun format(totatAmount: BigDecimal): String? {
        val usdCostFormat = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH)
        usdCostFormat.minimumFractionDigits = 1
        usdCostFormat.maximumFractionDigits = 2
        return usdCostFormat.format(totatAmount)
    }
}