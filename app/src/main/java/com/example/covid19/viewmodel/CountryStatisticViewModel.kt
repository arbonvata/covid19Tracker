package com.example.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.http.Covid19ApiInterface
import com.example.covid19.models.CountryStatistic
import kotlinx.coroutines.launch

class CountryStatisticViewModel : ViewModel() {
    var countryStatisticList = ArrayList<CountryStatistic>()
    val countryStatisticData: MutableLiveData<List<CountryStatistic>> by lazy {
        MutableLiveData<List<CountryStatistic>>()
    }

    suspend fun getCountryStatistic(countryId: String): MutableLiveData<List<CountryStatistic>> {
        //todo: Replace with async and await. More readable
        val launch = viewModelScope.launch {
            countryStatisticList = Covid19ApiInterface.getCountryStatistic(countryId)
            //TODO: Check that countryStatisticList is not empty
            if (!countryStatisticList.isEmpty()) {
                countryStatisticList =
                    countryStatisticList.reversed() as ArrayList<CountryStatistic>
            }
            countryStatisticData.value = countryStatisticList
        }
        launch.join()
        return countryStatisticData

    }
}