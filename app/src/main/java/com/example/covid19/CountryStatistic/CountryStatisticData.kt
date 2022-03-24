package com.example.covid19.CountryStatistic

import java.util.Date

data class CountryStatisticData(
    val ID: String,
    val Country: String,
    val CountryCode: String,
    val Province: String,
    val City: String,
    val CityCode: String,
    val Lat: Double,
    val Lon: Double,
    val Confirmed: Int,
    val Cases: Int,
    val Deaths: Int,
    val Recovered: Int,
    val Status: String,
    val Date: Date,

)