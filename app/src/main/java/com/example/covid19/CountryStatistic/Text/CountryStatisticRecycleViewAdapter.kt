package com.example.covid19.CountryStatistic.Text

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.CountryStatistic.CountryStatisticData
import com.example.covid19.R

class CountryStatisticRecycleViewAdapter(private val list: List<CountryStatisticData>) :
    RecyclerView.Adapter<CountryStatisticRecycleViewAdapter.CountryStatisticViewHolder>() {

    class CountryStatisticViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.country_statistic_row, parent, false)) {

        private var mDateTextView: TextView? = null
        private var mConfirmedTextView: TextView? = null
        private var mRecoveredTextView: TextView? = null
        private var mDeathsTextView: TextView? = null

        init {
            mDateTextView = itemView.findViewById(R.id.dateView)
            mConfirmedTextView = itemView.findViewById(R.id.confirmedView)
            mRecoveredTextView = itemView.findViewById(R.id.recovered)
            mDeathsTextView = itemView.findViewById(R.id.deaths)
        }

        fun bind(countryStatistic: CountryStatisticData) {
            mDateTextView?.text = "Date ${countryStatistic.Date}"
            mConfirmedTextView?.text = "Confirmed ${countryStatistic.Confirmed}"
            mRecoveredTextView?.text = "Recovered ${countryStatistic.Recovered}"
            mDeathsTextView?.text = "Number of deaths: ${countryStatistic.Deaths}"
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
        val countryStatistic: CountryStatisticData = list[position]
        holder.bind(countryStatistic)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
