package com.example.covid19.http

import com.example.covid19.models.Country
import com.example.covid19.models.CountryStatistic
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
// see https://developer.android.com/training/basics/network-ops/connecting
interface Covid19ApiInterface {
    @GET("countries")
    suspend fun getCountryList() : ArrayList<Country>

    @GET("country/{countryId}")
    suspend fun getCountryStatistic(@Path("country") countryId : String) : ArrayList<CountryStatistic>

    companion object {
        var BASE_URL : String ="https://covid19api.com/"
        private val client = OkHttpClient.Builder().build()

        fun create() : Covid19ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build()
            return retrofit.create(Covid19ApiInterface::class.java)

        }
    }

}