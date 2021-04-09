package com.example.covid19


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.covid19.fragments.CountryListFragment
import com.example.covid19.fragments.CountryStatisticFragment
import com.example.covid19.fragments.onClickedListener


class MainActivity : AppCompatActivity(), onClickedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //first time the app is launched
        if (savedInstanceState == null) {
            setCountryFragment()
        }
    }

    private fun setCountryFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<CountryListFragment>(R.id.fragment_container_view)

        }
    }

    private fun changeFragment(arg: String) {
        lateinit var nextFragment: Fragment
        val currentFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        if (currentFragment != null && currentFragment is CountryListFragment) {
            nextFragment = CountryStatisticFragment.newInstance(arg)

        } else {
            nextFragment = CountryListFragment.newInstance()


        }
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, nextFragment)
            addToBackStack(null)
        }
    }

    override fun onItemClicked(arg: String) {
        changeFragment(arg)
    }
}