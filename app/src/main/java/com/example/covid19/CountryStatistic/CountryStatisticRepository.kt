package com.example.covid19.CountryStatistic

import com.example.covid19.http.Covid19ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CountryStatisticRepository {
    suspend fun countryStatisticList(countryId: String): ArrayList<CountryStatisticData> {
        return withContext(Dispatchers.IO) {
            Covid19ApiInterface.getCountryStatistic(countryId)
        }
    }
}
