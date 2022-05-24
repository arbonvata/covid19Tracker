package com.example.covid19.countryStatistic.Text

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.countryStatistic.CountryStatisticData
import com.example.covid19.countryStatistic.Timeline

class CountryStatisticRecycleViewAdapter(private val data: CountryStatisticData) :
    RecyclerView.Adapter<CountryStatisticRecycleViewAdapter.CountryStatisticViewHolder>(), Filterable {
    private val list: ArrayList<Timeline>? = data.data?.timeline
    var filterData = list

    inner class CountryStatisticViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.country_statistic_row, parent, false)) {

        private var dateTextView: TextView? = null
        private var confirmedTextView: TextView? = null
        private var deathsTextView: TextView? = null
        private var newRecoveredTextView: TextView? = null

        init {
            dateTextView = itemView.findViewById(R.id.dateView)
            confirmedTextView = itemView.findViewById(R.id.confirmedView)
            deathsTextView = itemView.findViewById(R.id.death)
            newRecoveredTextView = itemView.findViewById(R.id.newRecovered)
        }

        fun bind(countryStatistic: Timeline) {
            dateTextView?.text = "Date ${countryStatistic.date}"
            confirmedTextView?.text = "Confirmed ${countryStatistic.confirmed}"
            deathsTextView?.text = "Deaths ${countryStatistic.deaths}"
            newRecoveredTextView?.text = "New recovered: ${countryStatistic.newConfirmed}"
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
        val countryStatistic = filterData?.get(position)
        countryStatistic?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return filterData?.size ?: 0
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterData = if (charSearch.isEmpty()) {
                    list
                } else {
                    val results = ArrayList<Timeline>()
                    list?.forEach {
                        if (it.date?.contains(charSearch, ignoreCase = true) == true) {
                            results.add(it)
                        }
                    }
                    results
                }
                val filterResults = FilterResults()
                filterResults.values = filterData
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterData = results?.values as ArrayList<Timeline> /* = java.util.ArrayList<com.example.covid19.CountryStatistic.Timeline> */
                notifyDataSetChanged()
            }
        }
    }
}
