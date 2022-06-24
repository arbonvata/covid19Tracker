package com.example.covid19.countryStatistic.Chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.covid19.Extensions.longFromStringDate
import com.example.covid19.countryStatistic.CountryStatisticData
import com.example.covid19.countryStatistic.CountryStatisticViewModel
import com.example.covid19.countryStatistic.Timeline
import com.example.covid19.databinding.StatisticChartFragmentBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.EntryXComparator
import java.text.SimpleDateFormat
import java.util.Collections
import java.util.Date
import kotlin.collections.ArrayList

class StatisticChartFragment : Fragment() {
    private val args by navArgs<StatisticChartFragmentArgs>()
    private val viewModel: CountryStatisticViewModel by viewModels()
    private lateinit var bindining: StatisticChartFragmentBinding
    private lateinit var chart: LineChart
    private val activeCases = mutableListOf<Entry>()
    private val deathCases = mutableListOf<Entry>()
    private val recoveredCases = mutableListOf<Entry>()
    private val confirmedCases = mutableListOf<Entry>()
    private val newRecoveredCases = mutableListOf<Entry>()
    private val newConfirmedCases = mutableListOf<Entry>()
    private val dataSets = mutableListOf<ILineDataSet>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindining = StatisticChartFragmentBinding.inflate(inflater)
        return bindining.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = args.countryName
        setupGraph()
        viewModel.countryStatisticData.observe(viewLifecycleOwner) {
            renderData(it)
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getCountryStatistic(args.countryId)
    }

    private fun renderData(statisticData: CountryStatisticData?) {
        val data: ArrayList<Timeline>? = statisticData?.data?.timeline
        data?.forEach { timeline ->
            val active = timeline.active
            val deaths = timeline.deaths
            val confirmed = timeline.confirmed
            val recovered = timeline.recovered
            val newConfirmed = timeline.newConfirmed
            val newRecovered = timeline.newRecovered
            val date = timeline.date
            date?.let {
                addEntryData(it, active, deaths, recovered, confirmed, newConfirmed, newRecovered)
            }
        }
        sortEntries()
        addDataSets()
    }

    private fun sortEntries() {
        Collections.sort(deathCases, EntryXComparator())
        Collections.sort(confirmedCases, EntryXComparator())
        Collections.sort(recoveredCases, EntryXComparator())
        Collections.sort(newConfirmedCases, EntryXComparator())
        Collections.sort(activeCases, EntryXComparator())
        Collections.sort(newRecoveredCases, EntryXComparator())
    }

    private fun addEntryData(
        it: String,
        active: Int?,
        deaths: Int?,
        recovered: Int?,
        confirmed: Int?,
        newConfirmed: Int?,
        newRecovered: Int?
    ) {
        val dateAsLong = it.longFromStringDate()

        activeCases.add(Entry(dateAsLong.toFloat(), active!!.toFloat()))
        deathCases.add(Entry(dateAsLong.toFloat(), deaths!!.toFloat(),))
        recoveredCases.add(Entry(dateAsLong.toFloat(), recovered!!.toFloat(),))
        confirmedCases.add(Entry(dateAsLong.toFloat(), confirmed!!.toFloat()))
        newConfirmedCases.add(Entry(dateAsLong.toFloat(), newConfirmed!!.toFloat()))
        newRecoveredCases.add(Entry(dateAsLong.toFloat(), newRecovered!!.toFloat()))
    }

    private fun addDataSets() {
        val lineDataSetActiveCases = LineDataSet(activeCases, "Active").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.BLUE
            setDrawCircles(false)
        }

        val lineDataSetDeaths = LineDataSet(deathCases, "Deaths").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.RED
            setDrawCircles(false)
        }
        val lineDataSetsRecovered = LineDataSet(recoveredCases, "Recovered").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.GREEN
            setDrawCircles(false)
        }
        val lineDataSetsConfirmed = LineDataSet(recoveredCases, "Confirmed").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.YELLOW
            setDrawCircles(false)
        }
        val lineDataSetsNewConfirmed = LineDataSet(recoveredCases, "New confirmed").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.CYAN
            setDrawCircles(false)
        }
        val lineDataSetsNewRecovered = LineDataSet(recoveredCases, "Confirmed").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.BLACK
            setDrawCircles(false)
        }

        with(dataSets) {
            add(lineDataSetActiveCases)
            add(lineDataSetDeaths)
            add(lineDataSetsRecovered)
            add(lineDataSetsConfirmed)
            add(lineDataSetsNewConfirmed)
            add(lineDataSetsNewRecovered)
        }
        with(chart) {
            data = LineData(dataSets)
            invalidate()
            notifyDataSetChanged()
            refreshDrawableState()
        }
    }

    private fun setupGraph() {

        chart = bindining.linechart

        with(chart) {
            setTouchEnabled(true)
            setPinchZoom(true)
            isDoubleTapToZoomEnabled = true
            viewPortHandler.setMaximumScaleX(5f)
            viewPortHandler.setMaximumScaleY(5f)
        }
        chart.xAxis.apply {
            valueFormatter = LineChartAxisValueFormatter()
        }
    }
    inner class LineChartAxisValueFormatter() : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val date = Date(value.toLong())
            val format = SimpleDateFormat("yyyy-MM-dd")
            val datum = format.format(date)
            return datum
        }
    }
}
