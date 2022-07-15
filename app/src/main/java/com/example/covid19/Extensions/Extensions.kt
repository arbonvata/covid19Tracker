package com.example.covid19.Extensions

import java.text.SimpleDateFormat

fun String.longFromStringDate(): Long {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = simpleDateFormat.parse(this)
    return date.time
}
