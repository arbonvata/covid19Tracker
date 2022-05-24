package com.example.covid19.countryStatistic.Chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.covid19.R

class StatisticChartFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticChartFragment()
    }

    private lateinit var viewModel: StatisticChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.statistic_chart_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticChartViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
