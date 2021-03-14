package com.example.covid19.recycleViews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.models.Country

class CountryRecycleViewAdapter(private val list: ArrayList<Country>) : RecyclerView.Adapter<CountryRecycleViewAdapter.CountryViewHolder>() {


    class CountryViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.country_name_and_code, parent, false))
    {
        private var mCountryNameTv: TextView? = null
        private var mSluggCodeTv: TextView? = null

        init {
            mCountryNameTv = itemView.findViewById(R.id.countryName)
            mSluggCodeTv = itemView.findViewById(R.id.countryId)
        }

        fun bind(country: Country) {
            mCountryNameTv?.text = country.country
            mSluggCodeTv?.text = country.Slug
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country : Country = list[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

