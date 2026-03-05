package com.rocket.movielab.domain.extensions

import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.getYear(): String {
    val formatters = listOf(
        DateTimeFormatter.ISO_LOCAL_DATE,
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
    )
    for (formatter in formatters) {
        try {
            return LocalDate.parse(this, formatter).year.toString()
        } catch (_: Exception) {
        }
    }
    Log.e("String.getYear", "This: $this\nError: Invalid format",)
    return ""
}
fun String.toBrazilianDate(): String{
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val date = LocalDate.parse(this, inputFormatter)
        date.format(outputFormatter)
    } catch (e: Exception) {
        Log.e("toBrazilianDate", "This: $this\nError: $e", )
        ""
    }
}
