package com.example.covid19.recycleViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.models.Country
import com.example.covid19.recycleViews.CountryRecycleViewAdapter.CountryViewHolder


class CountryRecycleViewAdapter(
    private val list: ArrayList<Country>,
    private val listener: OnItemClickListener
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
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = list[position]
                listener.onItemClick(item.Slug)
            }
            mSluggCodeTv?.text?.toString()?.let { listener.onItemClick(it) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(arg: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country: Country = list[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

