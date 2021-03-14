package com.example.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.models.CountryStatistic
import com.example.covid19.repositories.CountryRepository

class CountryStatisticViewModel : ViewModel() {
    var countryStatisticList = ArrayList<CountryStatistic>()
    val countryStatisticData: MutableLiveData<List<CountryStatistic>> by lazy {
        MutableLiveData<List<CountryStatistic>>()
    }

    init {
        countryStatisticList = CountryRepository.getAllCountriStatisticList()
        //fetch country statistic from
        countryStatisticData.value = countryStatisticList

    }
}