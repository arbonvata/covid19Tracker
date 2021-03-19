package com.example.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.models.Country
import com.example.covid19.repositories.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    var countries = ArrayList<Country>()
    val countriesData: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>()
    }

    suspend fun getAllCountries(): MutableLiveData<List<Country>> {
        //todo: Replace with async and await. More readable
        val jobInfo = viewModelScope.launch {

            countries = CountryRepository.getAllCountries()
            //sort alfabetically
            val countriesSorted = countries.sortedBy { it.Country }
            countriesData.value = countriesSorted.toList()
        }
        jobInfo.join()
        return countriesData
    }

}
