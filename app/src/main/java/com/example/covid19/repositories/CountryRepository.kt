package com.example.covid19.repositories

import com.example.covid19.http.Covid19ApiInterface
import com.example.covid19.models.Country
import com.example.covid19.models.CountryStatistic

object CountryRepository {
    private lateinit var allCountries: ArrayList<Country>

    private lateinit var countryStatisticList: ArrayList<CountryStatistic>


    suspend fun getAllCountries(): ArrayList<Country> {

        // TODO: fetch all countries and return the list
        allCountries = Covid19ApiInterface.create().getCountryList()
        return allCountries
    }

    suspend fun getAllCountriStatisticList(): ArrayList<CountryStatistic> {
        //ToDO: fetch all data from covid 19api
        countryStatisticList = Covid19ApiInterface.create().getCountryStatistic("")

        return countryStatisticList
    }

}