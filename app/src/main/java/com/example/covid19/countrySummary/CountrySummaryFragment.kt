package com.example.covid19.countrySummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.covid19.R
import com.example.covid19.countryStatistic.CountryStatisticViewModel
import com.example.covid19.databinding.FragmentSummaryBinding

class CountrySummaryFragment : Fragment() {
    private lateinit var countryCode: String
    private val viewModel: CountryStatisticViewModel by viewModels()
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
    ): View {
        binding = FragmentSummaryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCountryStatistic(countryId = args.countryId)
        activity?.title = args.countryName
        observeLiveData()
    }

    private fun observeLiveData() {
        // TODO: Fix warning Do not concatenate text displayed with setText. Use resource string with placeholders
        viewModel.countryStatisticData.observe(viewLifecycleOwner) {
            binding.deaths.text = getString(R.string.deaths, it.data?.latestData?.deaths)
            binding.deathRate.text = getString(R.string.death_rate, it.data?.latestData?.calculated?.deathRate)
            binding.casesPerMillionPopulation.text = getString(R.string.case_per_million, it.data?.latestData?.calculated?.casesPerMillionPopulation)
            binding.population.text = getString(R.string.population, it.data?.population)
            binding.recovery.text = getString(R.string.recovery, it.data?.latestData?.recovered)
            binding.critical.text = getString(R.string.critical, it.data?.latestData?.critical)
        }
    }
}
