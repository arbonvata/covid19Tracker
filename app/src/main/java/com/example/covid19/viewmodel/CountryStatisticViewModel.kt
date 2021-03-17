package com.example.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.http.Covid19ApiInterface
import com.example.covid19.models.CountryStatistic
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class CountryStatisticViewModel : ViewModel() {
    var countryStatisticList = ArrayList<CountryStatistic>()
    val countryStatisticData: MutableLiveData<List<CountryStatistic>> by lazy {
        MutableLiveData<List<CountryStatistic>>()
    }


    suspend fun getCountryStatistic(countryId: String): MutableLiveData<List<CountryStatistic>> {
        //todo: Replace with async and await. More readable
        val launch = viewModelScope.launch {
            withContext(IO) {
                countryStatisticList = Covid19ApiInterface.getCountryStatistic(countryId)
                countryStatisticList = countryStatisticList.reversed() as ArrayList<CountryStatistic>
            }
            withContext(Main) {
                countryStatisticData.value = countryStatisticList
            }
        }
        launch.join()

        return countryStatisticData
    }
}