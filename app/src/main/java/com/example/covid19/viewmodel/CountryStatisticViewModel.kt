package com.example.covid19.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.http.Covid19ApiInterface
import com.example.covid19.models.CountryStatistic
import com.example.covid19.repositories.CountryRepository
import kotlinx.coroutines.launch

class CountryStatisticViewModel : ViewModel() {
    private var countryStatisticList = ArrayList<CountryStatistic>()
    private val _countryStatisticData: MutableLiveData<List<CountryStatistic>> by lazy {
        MutableLiveData<List<CountryStatistic>>()
    }
    val countryStatisticData : LiveData<List<CountryStatistic>> = _countryStatisticData

    fun getCountryStatistic(countryId : String) {
        //todo: Replace with async and await. More readable
        viewModelScope.launch {
            countryStatisticList = CountryRepository.getAllCountriStatisticList(countryId)
            //TODO: Check that countryStatisticList is not empty
            if (!countryStatisticList.isEmpty()) {
                countryStatisticList =
                    countryStatisticList.reversed() as ArrayList<CountryStatistic>
            }
            _countryStatisticData.value = countryStatisticList
        }
    }
}