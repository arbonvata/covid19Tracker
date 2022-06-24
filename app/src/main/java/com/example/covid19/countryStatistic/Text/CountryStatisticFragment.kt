package com.example.covid19.countryStatistic.Text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.countryStatistic.CountryStatisticData
import com.example.covid19.countryStatistic.CountryStatisticViewModel
import com.example.covid19.databinding.FragmentCountryStatisticBinding
import com.example.covid19.recycleViews.DefaultItemDecorator

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class CountryStatisticFragment : Fragment() {
    private lateinit var recycleView: RecyclerView
    private lateinit var countryStatisticViewModel: CountryStatisticViewModel
    private val args: CountryStatisticFragmentArgs by navArgs()
    private lateinit var binding: FragmentCountryStatisticBinding
    private lateinit var recyclerViewAdapter: CountryStatisticRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = args.countryName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = initUI(inflater)
        initiateViewModel()
        registerSearchAction()
        observeStatisticData()
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
                // Not all countries reports their covid19
                if (it.data?.timeline?.isNotEmpty() == true) {
                    populateRecycleView(it)
                    // For those countries without any statistic show an informative textview
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
        countryStatisticViewModel.getCountryStatistic(args.countryId)
    }

    private fun populateRecycleView(listWithCountryStatistic: CountryStatisticData) {
        recyclerViewAdapter =
            CountryStatisticRecycleViewAdapter(listWithCountryStatistic)
        recycleView.apply {
            addItemDecoration(DefaultItemDecorator(12, 62))
            adapter = recyclerViewAdapter
        }
    }
}
