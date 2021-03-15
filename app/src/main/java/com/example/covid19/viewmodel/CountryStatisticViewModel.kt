package com.example.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.http.Covid19ApiInterface
import com.example.covid19.models.CountryStatistic

class CountryStatisticViewModel : ViewModel() {
    var countryStatisticList = ArrayList<CountryStatistic>()
    val countryStatisticData: MutableLiveData<List<CountryStatistic>> by lazy {
        MutableLiveData<List<CountryStatistic>>()
    }


    suspend fun getCountryStatistic(countryId: String): MutableLiveData<List<CountryStatistic>> {
        countryStatisticList = Covid19ApiInterface.create().getCountryStatistic(countryId)
        countryStatisticData.value = countryStatisticList
        return countryStatisticData
    }
}