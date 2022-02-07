package com.example.covid19.Country

import android.content.Context
import com.example.covid19.R
import com.example.covid19.http.Covid19ApiInterface
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

object CountryRepository {
    suspend fun getAllCountries(context: Context): ArrayList<Country> {
        return withContext(IO) {
            try {
                return@withContext Covid19ApiInterface.getCountryList()
            } catch (cause: Throwable) {
                throw Exception(context.getString(R.string.no_internet_connection))
            }
        }
    }
}
