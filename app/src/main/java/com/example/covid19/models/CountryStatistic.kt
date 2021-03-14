package com.example.covid19.models

import java.util.*

data class CountryStatistic(
    val country: String,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val date: Date
)
