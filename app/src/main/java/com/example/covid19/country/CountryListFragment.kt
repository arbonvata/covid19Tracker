package com.example.covid19.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.country.Country
import com.example.covid19.country.CountryRecycleViewAdapter
import com.example.covid19.country.CountryViewModel
import com.example.covid19.databinding.FragmentCountryListBinding
import com.example.covid19.recycleViews.DefaultItemDecorator

class CountryListFragment : Fragment() {
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var recycleView: RecyclerView
    private lateinit var binding: FragmentCountryListBinding
    private lateinit var recyclerViewAdapter: CountryRecycleViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryListBinding.inflate(inflater)
        val view = binding.root

        countryViewModel = ViewModelProvider(requireActivity()).get(CountryViewModel::class.java)
        activity?.title = requireContext().resources.getString(R.string.list_with_countries)
        registerSearchAction()
        registerObservers(view)
        return view
    }

    private fun registerSearchAction() {
        binding.countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerViewAdapter.filter.filter(newText.toString())
                return false
            }
        })
    }

    private fun registerObservers(view: LinearLayout) {
        recycleView = binding.countryListRv
        recycleView.layoutManager = LinearLayoutManager(this.context)

        countryViewModel.countriesData.observe(requireActivity()) {
            recyclerViewAdapter =
                CountryRecycleViewAdapter(it as ArrayList<Country>) { countryCode: String ->
                    // val directions = CountryListFragmentDirections.actionGoToStatisticFragment(countryCode)
                    val action =
                        CountryListFragmentDirections.actionGoToStatisticFragment(countryCode)
                    Navigation.findNavController(view).navigate(action)
                }

            recycleView.apply {
                addItemDecoration(DefaultItemDecorator(12, 62))
                adapter = recyclerViewAdapter
            }
        }
    }
}
