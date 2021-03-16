package com.example.covid19.http

import com.example.covid19.models.Country
import com.example.covid19.models.CountryStatistic
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.request.*


// see https://developer.android.com/training/basics/network-ops/connecting
object Covid19ApiInterface {

    private val client = HttpClient()
    private val gson = Gson()

    suspend fun getCountryList(): ArrayList<Country> {
        val response = client.get<String>("https://api.covid19api.com/countries")
        val itemType = object : TypeToken<ArrayList<Country>>() {}.type
        val countryList = gson.fromJson<ArrayList<Country>>(response, itemType)
        return countryList
    }

    suspend fun getCountryStatistic(countryId: String): ArrayList<CountryStatistic> {
        val response = client.get<String>("https://api.covid19api.com/country/$countryId")
        val itemType = object : TypeToken<ArrayList<CountryStatistic>>() {}.type
        val countryStatisticList = gson.fromJson<ArrayList<CountryStatistic>>(response, itemType)
        return countryStatisticList
    }
}