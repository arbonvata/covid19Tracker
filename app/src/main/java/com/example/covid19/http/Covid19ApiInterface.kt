package com.example.covid19.http

import com.example.covid19.Country.Country
import com.example.covid19.CountryStatistic.CountryStatisticData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.request.get

// see https://developer.android.com/training/basics/network-ops/connecting
object Covid19ApiInterface {

    private val client = HttpClient()
    private val gson = Gson()

    suspend fun getCountryList(): ArrayList<Country> {
        val response = client.get<String>("https://api.covid19api.com/countries")
        val itemType = object : TypeToken<ArrayList<Country>>() {}.type
        return gson.fromJson(response, itemType)
    }

    suspend fun getCountryStatistic(countryId: String): ArrayList<CountryStatisticData> {
        val response = client.get<String>("https://api.covid19api.com/country/$countryId")
        val itemType = object : TypeToken<ArrayList<CountryStatisticData>>() {}.type
        return gson.fromJson(response, itemType)
    }
}
