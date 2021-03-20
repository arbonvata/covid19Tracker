package com.example.covid19.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.models.CountryStatistic
import com.example.covid19.recycleViews.CountryStatisticRecycleViewAdapter
import com.example.covid19.recycleViews.DefaultItemDecorator
import com.example.covid19.viewmodel.CountryStatisticViewModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [CountryStatisticFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountryStatisticFragment : Fragment() {
    private lateinit var countryCode: String
    lateinit var recycleView: RecyclerView
    lateinit var countryStatisticViewModel: CountryStatisticViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryCode = it.getString(ARG_PARAM1).toString()

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_country_statistic, container, false)
        recycleView = view.findViewById(R.id.countryStatisticRv)
        recycleView.layoutManager = LinearLayoutManager(this.context)
        initiateViewModel()
        return view
    }

    private fun initiateViewModel() {
        countryStatisticViewModel =
            ViewModelProvider(activity!!).get(CountryStatisticViewModel::class.java)
        lifecycleScope.launch {
            val allData: MutableLiveData<List<CountryStatistic>>

            allData = countryStatisticViewModel.getCountryStatistic(countryCode)

            val listWithCountryStatistic = allData.value
            if (listWithCountryStatistic != null) {
                populateRecycleView(listWithCountryStatistic)
            }


        }
    }

    private fun populateRecycleView(listWithCountryStatistic: List<CountryStatistic>) {
        recycleView.addItemDecoration(DefaultItemDecorator(12, 62))
        val recyclerViewAdapter: CountryStatisticRecycleViewAdapter =
            CountryStatisticRecycleViewAdapter(listWithCountryStatistic)
        recycleView.adapter = recyclerViewAdapter

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CountryStatisticFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(countryCode: String) =
            CountryStatisticFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, countryCode)
                }
            }
    }
}