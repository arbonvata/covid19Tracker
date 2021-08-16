package com.example.covid19.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.models.Country
import com.example.covid19.repositories.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    var countries = ArrayList<Country>()
    private val _countriesData: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>()
    }
    val countriesData : LiveData<List<Country>> = _countriesData

     init {
         viewModelScope.launch {
             countries = CountryRepository.getAllCountries()
             //sort alfabetically
             val countriesSorted = countries.sortedBy { it.Country }
             _countriesData.value = countriesSorted.toList()
         }
    }

}
