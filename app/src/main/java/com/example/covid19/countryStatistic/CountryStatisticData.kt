package com.example.covid19.countryStatistic

import com.google.gson.annotations.SerializedName
/*
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
*/
data class CountryStatisticData(
    @SerializedName("data") var data: Data? = null,
    @SerializedName("_cacheHit") var CacheHit: Boolean? = null

)
data class Coordinates(

    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null

)
data class Today(

    @SerializedName("deaths") var deaths: Int? = null,
    @SerializedName("confirmed") var confirmed: Int? = null

)
data class Calculated(

    @SerializedName("death_rate") var deathRate: Double? = null,
    @SerializedName("recovery_rate") var recoveryRate: Double? = null,
    @SerializedName("recovered_vs_death_ratio") var recoveredVsDeathRatio: String? = null,
    @SerializedName("cases_per_million_population") var casesPerMillionPopulation: Int? = null

)

data class LatestData(

    @SerializedName("deaths") var deaths: Int? = null,
    @SerializedName("confirmed") var confirmed: Int? = null,
    @SerializedName("recovered") var recovered: Int? = null,
    @SerializedName("critical") var critical: Int? = null,
    @SerializedName("calculated") var calculated: Calculated? = Calculated()

)

data class Timeline(

    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("deaths") var deaths: Int? = null,
    @SerializedName("confirmed") var confirmed: Int? = null,
    @SerializedName("recovered") var recovered: Int? = null,
    @SerializedName("new_confirmed") var newConfirmed: Int? = null,
    @SerializedName("new_recovered") var newRecovered: Int? = null,
    @SerializedName("new_deaths") var newDeaths: Int? = null,
    @SerializedName("active") var active: Int? = null

)

data class Data(

    @SerializedName("coordinates") var coordinates: Coordinates? = Coordinates(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("population") var population: Int? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("today") var today: Today? = Today(),
    @SerializedName("latest_data") var latestData: LatestData? = LatestData(),
    @SerializedName("timeline") var timeline: ArrayList<Timeline> = arrayListOf()

)
