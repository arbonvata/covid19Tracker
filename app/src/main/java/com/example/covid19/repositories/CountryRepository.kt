package com.example.covid19.repositories

import com.example.covid19.models.Country
import com.example.covid19.models.CountryStatistic

object CountryRepository {
    private lateinit var allCountries: ArrayList<Country>

    private lateinit var countryStatisticList: ArrayList<CountryStatistic>


    fun getAllCountries(): ArrayList<Country> {
        // TODO: fetch all countries and return the list
        allCountries = CountryRepository.getAllCountries()
        return allCountries
    }

    fun getAllCountriStatisticList(): ArrayList<CountryStatistic> {
        //ToDO: fetch all data from covid 19api

        return countryStatisticList
    }

}