package com.example.covid19.CountryStatistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.recycleViews.DefaultItemDecorator

/**
 * A simple [Fragment] subclass.
 * Use the [CountryStatisticFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountryStatisticFragment : Fragment() {
    private lateinit var countryCode: String
    lateinit var recycleView: RecyclerView
    lateinit var countryStatisticViewModel: CountryStatisticViewModel
    private val args: CountryStatisticFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryCode = args.countryId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_country_statistic, container, false)
        recycleView = view.findViewById(R.id.countryStatisticRv)
        recycleView.layoutManager = LinearLayoutManager(this.context)
        initiateViewModel()
        countryStatisticViewModel.countryStatisticData.observe(requireActivity()) {
            it?.let {
                populateRecycleView(it)
            }
        }
        return view
    }

    private fun initiateViewModel() {
        countryStatisticViewModel =
            ViewModelProvider(requireActivity()).get(CountryStatisticViewModel::class.java)
        countryStatisticViewModel.getCountryStatistic(countryCode)
    }

    private fun populateRecycleView(listWithCountryStatistic: List<CountryStatistic>) {
        val recyclerViewAdapter =
            CountryStatisticRecycleViewAdapter(listWithCountryStatistic)
        recycleView.apply {
            addItemDecoration(DefaultItemDecorator(12, 62))
            adapter = recyclerViewAdapter
        }
    }
}
