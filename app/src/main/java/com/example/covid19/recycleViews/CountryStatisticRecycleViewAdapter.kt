package com.example.covid19.recycleViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.models.CountryStatistic


class CountryStatisticRecycleViewAdapter(private val list: List<CountryStatistic>) :
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

        fun bind(countryStatistic: CountryStatistic) {
            mDateTextView?.text = countryStatistic.Date.toString()
            mConfirmedTextView?.text = countryStatistic.Confirmed.toString()
            mRecoveredTextView?.text = countryStatistic.Recovered.toString()
            mDeathsTextView?.text = countryStatistic.Deaths.toString()
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
            holder: CountryStatisticRecycleViewAdapter.CountryStatisticViewHolder,
            position: Int
    ) {
        val countryStatistic: CountryStatistic = list[position]
        holder.bind(countryStatistic)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}