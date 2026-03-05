package com.rocket.movielab.domain.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.toBrazilian(fractionDigits: Int = 2): String {
    val format = NumberFormat.getNumberInstance(Locale("pt", "BR"))
    format.minimumFractionDigits = fractionDigits
    format.maximumFractionDigits = fractionDigits
    format.isGroupingUsed = true
    return format.format(this)
}

fun Int.toDuration(): String {
    val hours = this / 60
    val remainingMinutes = this % 60

    return when {
        hours > 0 && remainingMinutes > 0 -> "${hours}h ${remainingMinutes}min"
        hours > 0 -> "${hours}h"
        else -> "${remainingMinutes}min"
    }
}