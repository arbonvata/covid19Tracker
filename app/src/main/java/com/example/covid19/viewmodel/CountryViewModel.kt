package com.example.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.models.Country
import com.example.covid19.repositories.CountryRepository

class CountryViewModel : ViewModel() {
    var countries = ArrayList<Country>()
    val countriesData: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>()
    }

    init {
        countries = CountryRepository.getAllCountries()
        countriesData.value = countries
    }
}