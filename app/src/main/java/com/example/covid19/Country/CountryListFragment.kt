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
import com.example.covid19.databinding.FragmentCountryListBinding
import com.example.covid19.recycleViews.DefaultItemDecorator

class CountryListFragment : Fragment() {
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var recycleView: RecyclerView
    private lateinit var binding: FragmentCountryListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(inflater)
        val view = binding.root
        recycleView = binding.countryListRv

        recycleView.layoutManager = LinearLayoutManager(this.context)
        countryViewModel = ViewModelProvider(requireActivity()).get(CountryViewModel::class.java)
        activity?.title = "List with countries"

        countryViewModel.countriesData.observe(requireActivity()) {
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
