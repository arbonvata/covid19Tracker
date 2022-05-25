package com.example.covid19.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
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
    enum class PresentationMode {
        NOT_VALID,
        AS_TEXT,
        AS_SUMMARY,
        AS_GRAPH
    }
    private lateinit var mode: PresentationMode

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
        getPresentationMode()
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
                    val action = getAction(countryCode)
                    Navigation.findNavController(view).navigate(action)
                }

            recycleView.apply {
                addItemDecoration(DefaultItemDecorator(12, 62))
                adapter = recyclerViewAdapter
            }
        }
    }

    private fun getPresentationMode(): PresentationMode {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val value = sharedPref.getString(context?.getString(R.string.info_presentation_mode), "not valid")
        val array = requireContext().resources.getStringArray(R.array.mode_values)
        mode = when (value) {
            array[0] -> PresentationMode.AS_TEXT
            array[1] -> PresentationMode.AS_SUMMARY
            array[2] -> PresentationMode.AS_GRAPH
            else -> PresentationMode.NOT_VALID
        }
        return mode
    }

    private fun getAction(countryId: String): NavDirections {
        return when (mode) {
            PresentationMode.AS_TEXT -> {
                CountryListFragmentDirections.actionGoToStatisticFragment(countryId)
            }
            PresentationMode.AS_SUMMARY -> {
                CountryListFragmentDirections.actionGoToSummaryFragment(countryId)
            }
            PresentationMode.AS_GRAPH -> {
                CountryListFragmentDirections.actionGoToGraphFragment(countryId)
            }
            PresentationMode.NOT_VALID -> {
                CountryListFragmentDirections.actionGoToStatisticFragment(countryId)
            }
        }
    }
}
