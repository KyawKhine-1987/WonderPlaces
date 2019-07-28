package com.android.freelance.famousplaces.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.freelance.famousplaces.R
import com.android.freelance.famousplaces.data.network.WonderPlacesApiService
import com.android.freelance.famousplaces.data.network.ConnectivityInterceptorImpl
import com.android.freelance.famousplaces.data.network.WonderPlacesNetworkDataSourceImpl
import kotlinx.android.synthetic.main.activity_wonder_places_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class WonderPlacesListActivity : AppCompatActivity(), KodeinAware {

    private val LOG_TAG = WonderPlacesListActivity::class.java.name

    override val kodein by closestKodein()
    private val viewModelFactory: WonderPlacesListViewModelFactory by instance()
    private lateinit var viewModel: WonderPlacesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "TEST: onCreate() called...")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wonder_places_list)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WonderPlacesListViewModel::class.java)

        bindUI()
        /*val apiService = WonderPlacesApiService(ConnectivityInterceptorImpl(this.applicationContext!!))
        val wonderPlacesNetworkDataSource = WonderPlacesNetworkDataSourceImpl(apiService)
        wonderPlacesNetworkDataSource.downloadedWonderPlaces.observe(this, Observer {
            tvLocation.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            wonderPlacesNetworkDataSource.fetchWonderPlaces()}*/

    }

    private fun bindUI() = GlobalScope.launch {

        val wonderPlaces = viewModel.wonderPlaces.await()
        wonderPlaces.observe(this@WonderPlacesListActivity, Observer {

            if (it == null) return@Observer
            tvLocation.text = it.toString()
        })
    }
}
