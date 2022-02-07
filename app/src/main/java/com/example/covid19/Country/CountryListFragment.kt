package com.example.covid19.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.Country.Country
import com.example.covid19.Country.CountryRecycleViewAdapter
import com.example.covid19.Country.CountryViewModel
import com.example.covid19.R
import com.example.covid19.recycleViews.DefaultItemDecorator

class CountryListFragment : Fragment() {
    lateinit var countryViewModel: CountryViewModel
    lateinit var recycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_country_list, container, false)
        recycleView = view.findViewById(R.id.countryListRv)
        recycleView.layoutManager = LinearLayoutManager(this.context)
        countryViewModel = ViewModelProvider(requireActivity()).get(CountryViewModel::class.java)

        countryViewModel.countriesData.observe(requireActivity()) { it ->
            val recyclerViewAdapter =
                CountryRecycleViewAdapter(it as ArrayList<Country>) { countryCode: String ->
                    // val directions = CountryListFragmentDirections.actionGoToStatisticFragment(countryCode)
                    val action = CountryListFragmentDirections.actionGoToStatisticFragment(countryCode)
                    Navigation.findNavController(view).navigate(action)
                }

            recycleView.apply {
                addItemDecoration(DefaultItemDecorator(12, 62))
                adapter = recyclerViewAdapter
            }
        }
        return view
    }
}
