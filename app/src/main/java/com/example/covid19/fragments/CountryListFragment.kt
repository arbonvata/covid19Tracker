package com.example.covid19.fragments

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.http.Covid19ApiInterface
import com.example.covid19.models.Country
import com.example.covid19.recycleViews.CountryRecycleViewAdapter
import com.example.covid19.recycleViews.DefaultItemDecorator
import com.example.covid19.viewmodel.CountryViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CountryListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountryListFragment : Fragment(), CountryRecycleViewAdapter.OnItemClickListener {
    lateinit var countryModel : CountryViewModel
    lateinit var recycleView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_country_list, container, false)
        recycleView = view.findViewById(R.id.countryListRv)
        recycleView.layoutManager = LinearLayoutManager(this.context)

        countryModel = ViewModelProvider(activity!!).get(CountryViewModel::class.java)
        viewLifecycleOwner.lifecycleScope.launch {
            val allCountries: MutableLiveData<List<Country>>
            withContext(IO) {
                 allCountries = countryModel.getAllCountries()
            }
            withContext(Main) {
                val countries = allCountries.value
                if(countries != null) {
                    populateView(allCountries)
                }
            }
        }
        return view
    }

    private fun populateView(allCountries: MutableLiveData<List<Country>>) {
        val countryList = allCountries.value
        recycleView.addItemDecoration(DefaultItemDecorator(12, 62))
        val recyclerViewAdapter :  CountryRecycleViewAdapter = CountryRecycleViewAdapter(countryList as ArrayList<Country>, this)
        recycleView.adapter = recyclerViewAdapter

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CountryListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CountryListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(arg: String) {
        Log.d("Arbon", arg)
    }
}