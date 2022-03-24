package com.example.covid19.CountryStatistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.databinding.FragmentCountryStatisticBinding
import com.example.covid19.recycleViews.DefaultItemDecorator
import io.ktor.util.*

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
        activity?.title = countryCode.uppercase()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = initUI(inflater)
        initiateViewModel()
        observeStatisticData()
        return view
    }

    private fun initUI(inflater: LayoutInflater): LinearLayout {
        binding = FragmentCountryStatisticBinding.inflate(inflater)
        recycleView = binding.countryStatisticRv
        val view = binding.root
        recycleView.layoutManager = LinearLayoutManager(this.context)
        return view
    }

    private fun observeStatisticData() {
        countryStatisticViewModel.countryStatisticData.observe(requireActivity()) {
            it?.let {
                //Not all countries reports their covid19
                if (it.isNotEmpty()) {
                    populateRecycleView(it)
                } else {
                    binding.textView.visibility = View.VISIBLE
                    binding.countryStatisticRv.visibility = View.GONE
                    binding.textView.text = context?.getString(R.string.no_statistic_for_this_country)
                }
            }
        }
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
