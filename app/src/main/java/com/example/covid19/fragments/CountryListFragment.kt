package com.example.covid19.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.models.Country
import com.example.covid19.recycleViews.CountryRecycleViewAdapter
import com.example.covid19.recycleViews.CountryRecycleViewAdapter.OnItemClickListener
import com.example.covid19.recycleViews.DefaultItemDecorator
import com.example.covid19.viewmodel.CountryViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CountryListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountryListFragment : Fragment(), OnItemClickListener {
    lateinit var countryViewModel: CountryViewModel
    lateinit var recycleView: RecyclerView
    lateinit var clickedListener: onClickedListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_country_list, container, false)
        recycleView = view.findViewById(R.id.countryListRv)
        recycleView.layoutManager = LinearLayoutManager(this.context)
        countryViewModel = ViewModelProvider(requireActivity()).get(CountryViewModel::class.java)

        countryViewModel.countriesData.observe(requireActivity(), {
            val recyclerViewAdapter: CountryRecycleViewAdapter =
                CountryRecycleViewAdapter(it as ArrayList<Country>, this)
            recycleView.apply {
                addItemDecoration(DefaultItemDecorator(12, 62))
                adapter = recyclerViewAdapter
            }
            setListener(activity as onClickedListener)
        })
        return view
    }

    fun setListener(onItemClickedListener: onClickedListener) {
        this.clickedListener = onItemClickedListener
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment CountryListFragment.
         */
        @JvmStatic
        fun newInstance() =
            CountryListFragment()
    }

    override fun onItemClick(arg: String) = clickedListener?.let {
        it.onItemClicked(arg)
    }
}

interface onClickedListener {
    fun onItemClicked(arg: String)
}