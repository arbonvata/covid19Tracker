package com.example.covid19.Country

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.Country.CountryRecycleViewAdapter.CountryViewHolder
import com.example.covid19.R

class CountryRecycleViewAdapter(
    private val countryList: ArrayList<Country>,
    private val callBack: (String) -> Unit
) : RecyclerView.Adapter<CountryViewHolder>() {

    inner class CountryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.country_name_and_code, parent, false)),
        View.OnClickListener {
        private var mCountryNameTv: TextView? = null
        private var mSluggCodeTv: TextView? = null

        init {
            mCountryNameTv = itemView.findViewById(R.id.countryName)
            mSluggCodeTv = itemView.findViewById(R.id.countrySluggCodeTv)
            itemView.setOnClickListener(this)
        }

        fun bind(country: Country) {
            mCountryNameTv?.text = country.Country
            mSluggCodeTv?.text = country.Slug
        }

        override fun onClick(v: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = countryList[position]
                callBack(item.Slug)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country: Country = countryList[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
}
