package com.example.covid19.countryStatistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.countryStatistic.CountryStatisticRepository.countryStatisticList
import kotlinx.coroutines.launch

class CountryStatisticViewModel : ViewModel() {
    private lateinit var statisticData: CountryStatisticData
    private val _countryStatisticData: MutableLiveData<CountryStatisticData> by lazy {
        MutableLiveData<CountryStatisticData>()
    }
    val countryStatisticData: LiveData<CountryStatisticData> = _countryStatisticData

    fun getCountryStatistic(countryId: String) {
        viewModelScope.launch {
            statisticData = countryStatisticList(countryId)
            val list = statisticData.data?.timeline
            if (list != null) {
                if (list.isNotEmpty()) {

                    list.reversed()
                }
            }
            _countryStatisticData.value = statisticData
        }
    }
}
