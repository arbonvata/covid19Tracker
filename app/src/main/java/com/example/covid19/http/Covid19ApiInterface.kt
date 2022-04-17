package com.example.covid19.http

import com.example.covid19.Country.Country
import com.example.covid19.CountryStatistic.CountryStatisticData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod

// see https://developer.android.com/training/basics/network-ops/connecting
object Covid19ApiInterface {

    private val client = HttpClient() {
    }
    private val gson = Gson()

    suspend fun getCountryList(): ArrayList<Country> {
        val response = client.request("https://api.covid19api.com/countries") {
            method = HttpMethod.Get
        }
        val itemType = object : TypeToken<ArrayList<Country>>() {}.type
        return gson.fromJson(response.body<String>(), itemType)
    }

    suspend fun getCountryStatistic(countryId: String): CountryStatisticData {
        // "https://api.covid19api.com/country/$countryId"
        val response = client.request("https://corona-api.com/countries/$countryId") {
            method = HttpMethod.Get
        }
        val itemType = object : TypeToken<CountryStatisticData>() {}.type
        return gson.fromJson(response.bodyAsText(), itemType)
    }
}
