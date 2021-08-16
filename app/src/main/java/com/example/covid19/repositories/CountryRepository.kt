package com.example.covid19.repositories

import com.example.covid19.http.Covid19ApiInterface
import com.example.covid19.models.Country
import com.example.covid19.models.CountryStatistic
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext


object CountryRepository {
    suspend fun getAllCountries(): ArrayList<Country> {
        return withContext(IO) {
            Covid19ApiInterface.getCountryList()
        }
    }

    suspend fun getAllCountriStatisticList(countryId: String): ArrayList<CountryStatistic> {
        return  withContext(IO) {
            Covid19ApiInterface.getCountryStatistic(countryId)
        }
    }
}