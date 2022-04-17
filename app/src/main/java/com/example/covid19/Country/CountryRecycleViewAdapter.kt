package com.example.covid19.Country

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covid19.Country.CountryRecycleViewAdapter.CountryViewHolder
import com.example.covid19.R

class CountryRecycleViewAdapter(
    private val countryList: ArrayList<Country>,
    private val callBack: (String) -> Unit
) : RecyclerView.Adapter<CountryViewHolder>() {

    inner class CountryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.country_name_and_code, parent, false)),
        OnClickListener {
        var context: Context = parent.context
        private var countryNameTv: TextView = itemView.findViewById(R.id.countryName)
        private var sluggCodeTv: TextView = itemView.findViewById(R.id.countrySluggCodeTv)
        private var flagIcon: ImageView = itemView.findViewById(R.id.flag)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(country: Country) {
            countryNameTv.text = country.Country
            sluggCodeTv.text = country.Slug
            var url = "https://flagcdn.com/72x54/${country.ISO2.lowercase()}.webp"
            // Todo: a better solution is to download all flags into a database and cache them
            Glide.with(context).load(url).into(flagIcon)
        }

        override fun onClick(v: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = countryList[position]
                callBack(item.ISO2)
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
