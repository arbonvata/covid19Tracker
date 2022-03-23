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
import com.example.covid19.databinding.FragmentCountryStatisticBinding
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
    private lateinit var binding :FragmentCountryStatisticBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryCode = args.countryId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryStatisticBinding.inflate(inflater)
        recycleView = binding.countryStatisticRv
        val view = binding.root
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
