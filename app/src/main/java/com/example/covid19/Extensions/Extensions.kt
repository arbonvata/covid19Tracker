package com.example.covid19.Extensions

import java.text.SimpleDateFormat
import java.util.Date

fun String.longFromStringDate(): Long {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = simpleDateFormat.parse(this)
    return date.time
}

fun Long.dateFromLong(): String {
    val date = Date(this * 1)
    val format = SimpleDateFormat("yyyy-MM-dd")
    val datum = format.format(date)
    return datum
}
