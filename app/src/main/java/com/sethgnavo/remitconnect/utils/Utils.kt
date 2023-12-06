package com.sethgnavo.remitconnect.utils

import java.text.NumberFormat
import java.util.Locale

fun formatAmount(amount: Double?): String {

    if (amount == 0.0 || amount == null) {
        return "0"
    }
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = if (amount % 1.0 != 0.0) 1 else 0
        maximumFractionDigits = 2
    }
    return formatter.format(amount)
}