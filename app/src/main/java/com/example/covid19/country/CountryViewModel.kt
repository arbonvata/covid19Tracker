package com.example.covid19.country

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.covid19.R
import kotlinx.coroutines.launch
import java.lang.Exception

class CountryViewModel(application: Application) : AndroidViewModel(application) {
    var countries = ArrayList<Country>()
    val context = application.applicationContext
    private val _countriesData: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>()
    }
    val countriesData: LiveData<List<Country>> = _countriesData

    init {
        viewModelScope.launch {
            try {
                countries = CountryRepository.getAllCountries(context)
                // sort alfabetically
                val countriesSorted = countries.sortedBy { it.Country }
                _countriesData.value = countriesSorted.toList()
            } catch (e: Exception) {
                // TODO: Handle it better :)
                Toast.makeText(application.applicationContext, context.getString(R.string.no_internet), Toast.LENGTH_LONG).show()
            }
        }
    }
}
