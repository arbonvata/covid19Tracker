package com.example.covid19.CountryStatistic.Text

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.CountryStatistic.CountryStatisticData
import com.example.covid19.CountryStatistic.Timeline
import com.example.covid19.R

class CountryStatisticRecycleViewAdapter(private val data: CountryStatisticData) :
    RecyclerView.Adapter<CountryStatisticRecycleViewAdapter.CountryStatisticViewHolder>() {
    private val list: ArrayList<Timeline>? = data.data?.timeline

    class CountryStatisticViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.country_statistic_row, parent, false)) {


        private var dateTextView: TextView? = null
        private var confirmedTextView: TextView? = null
        private var recoveredTextView: TextView? = null
        private var deathsTextView: TextView? = null

        init {
            dateTextView = itemView.findViewById(R.id.dateView)
            confirmedTextView = itemView.findViewById(R.id.confirmedView)
            recoveredTextView = itemView.findViewById(R.id.recovered)
            deathsTextView = itemView.findViewById(R.id.deaths)
        }

        fun bind(countryStatistic: Timeline) {
            dateTextView?.text = "Date ${countryStatistic.date}"
            confirmedTextView?.text = "Confirmed ${countryStatistic.confirmed}"
            recoveredTextView?.text = "Recovered ${countryStatistic.recovered}"
            deathsTextView?.text = "Number of deaths: ${countryStatistic.recovered}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryStatisticViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CountryStatisticViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(
        holder: CountryStatisticViewHolder,
        position: Int
    ) {
        val countryStatistic = list?.get(position)
        countryStatistic?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}
