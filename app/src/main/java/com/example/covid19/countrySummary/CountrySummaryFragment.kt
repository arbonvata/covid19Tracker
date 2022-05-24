package com.example.covid19.countrySummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.covid19.countryStatistic.CountryStatisticViewModel
import com.example.covid19.databinding.FragmentSummaryBinding

class CountrySummaryFragment : Fragment() {
    private lateinit var countryCode: String
    private lateinit var viewModel: CountryStatisticViewModel
    private lateinit var binding: FragmentSummaryBinding
    private val args: CountrySummaryFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryCode = args.countryId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountryStatisticViewModel::class.java)
        viewModel.getCountryStatistic(countryId = countryCode)
        activity?.title = countryCode.uppercase()
        observeLiveData()
        // TODO: Use the ViewModel
    }

    private fun observeLiveData() {
        // TODO: Fix warning Do not concatenate text displayed with setText. Use resource string with placeholders
        viewModel.countryStatisticData.observe(viewLifecycleOwner) {
            binding.deaths.text = "Deaths ${it.data?.latestData?.deaths}"
            binding.deathRate.text = "Death rate ${it.data?.latestData?.calculated?.deathRate}"
            binding.casesPerMillionPopulation.text = "Case per million " + it.data?.latestData?.calculated?.casesPerMillionPopulation
            binding.population.text = "Population ${it.data?.population}"
            binding.recovery.text = "Recovery ${it.data?.latestData?.recovered}"
            binding.critical.text = "Critical ${it.data?.latestData?.critical}"
        }
    }
}
