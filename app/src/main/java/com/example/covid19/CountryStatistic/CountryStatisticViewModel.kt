package com.example.covid19.CountryStatistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountryStatisticViewModel : ViewModel() {
    private var countryStatisticList = ArrayList<CountryStatisticData>()
    private val _countryStatisticData: MutableLiveData<List<CountryStatisticData>> by lazy {
        MutableLiveData<List<CountryStatisticData>>()
    }
    val countryStatisticData: LiveData<List<CountryStatisticData>> = _countryStatisticData

    fun getCountryStatistic(countryId: String) {
        // todo: Replace with async and await. More readable
        viewModelScope.launch {
            countryStatisticList = CountryStatisticRepository.countryStatisticList(countryId)
            // TODO: Check that countryStatisticList is not empty
            if (!countryStatisticList.isEmpty()) {
                countryStatisticList =
                    countryStatisticList.reversed() as ArrayList<CountryStatisticData>
            }
            _countryStatisticData.value = countryStatisticList
        }
    }
}
