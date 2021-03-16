package com.example.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.models.Country
import com.example.covid19.repositories.CountryRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryViewModel : ViewModel() {
    var countries = ArrayList<Country>()
    val countriesData: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>()
    }

    suspend fun getAllCountries(): MutableLiveData<List<Country>> {

        val jobInfo = viewModelScope.launch {
            withContext(IO) {
                countries = CountryRepository.getAllCountries()
            }
            withContext(Main) {
                countriesData.value = countries

            }
        }
        jobInfo.join()
        return countriesData
    }

}
