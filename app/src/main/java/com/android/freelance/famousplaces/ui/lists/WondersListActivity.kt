package com.android.freelance.famousplaces.ui.lists

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.freelance.famousplaces.R
import com.android.freelance.famousplaces.data.db.entity.WondersEntity
import com.android.freelance.famousplaces.data.network.ApiWonders
import com.android.freelance.famousplaces.ui.adapters.WondersAdapters
import com.android.freelance.famousplaces.ui.viewmodel.WondersListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_wonders_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*class WondersListActivity : AppCompatActivity(), KodeinAware {*/
class WondersListActivity : AppCompatActivity() {

    private val LOG_TAG = WondersListActivity::class.java.name

    /*override val kodein by closestKodein()*/
    private lateinit var viewModel: WondersListViewModel
    var progressBar: ProgressBar? = null
    var hasInternet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "TEST: onCreate() called...")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wonders_list)

        viewModel = ViewModelProviders.of(this).get(WondersListViewModel::class.java)

        // ProgressBar
        progressBar = findViewById(R.id.pbLoadingIndicator)

        // display the progressbar
        progressBarLoading()

        bindUI()

        offlineData()

        /*val apiService = ApiWonders(ConnectivityInterceptorImpl(this.applicationContext!!))
        val wonderPlacesNetworkDataSource = WonderPlacesNetworkDataSourceImpl(apiService)
        wonderPlacesNetworkDataSource.downloadedWonderPlaces.observe(this, Observer {
            tvLocation.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            wonderPlacesNetworkDataSource.fetchWonderPlaces()}*/

    }

    @SuppressLint("CheckResult")
    private fun bindUI() {
        Log.i(LOG_TAG, "TEST: bindUI() is called...")
        /* val wonderPlaces = viewModel.wonderPlaces.await()
         wonderPlaces.observe(this@WondersListActivity, Observer {

             if (it == null) return@Observer
             tvLocation.text = it.toString()
         })*/

        viewModel.deleteAllData()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.myjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val apiMovies = retrofit.create(ApiWonders::class.java)

        apiMovies.getWonders()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (isNetworkAvailable()) {
                    hasInternet = true

                    Thread(Runnable {
                        val wondersListFromNetwork = it.wonders!!

                        val wondersListEntity = ArrayList<WondersEntity>()
                        for (wondersFromNetwork in wondersListFromNetwork) {

                            val wondersEntity = WondersEntity()
                            wondersEntity.location = wondersFromNetwork.location
                            wondersEntity.image = wondersFromNetwork.image
                            wondersEntity.description = wondersFromNetwork.description
                            wondersEntity.lat = wondersFromNetwork.lat
                            wondersEntity.long = wondersFromNetwork.long
                            wondersListEntity.add(wondersEntity)
                        }

                        viewModel.insert(wondersListEntity)
                        progressBarGone()
                    }).start()
                }
            }, {
                if (!hasInternet) {
                    Toast.makeText(
                        applicationContext,
                        "No Internet Connection!\nThis is offline data which is taken to show you from the local storage.\nAlso " + it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                progressBarGone()
            })
    }

    private fun offlineData() {
        Log.i(LOG_TAG, "TEST: offlineData() is called...")

        viewModel.fetchAllWonders.observe(this@WondersListActivity, Observer { wonders ->
            wonders?.let { refreshUIWith(wonders) }
        })
    }

    private fun refreshUIWith(wondersEntity: List<WondersEntity>) {
        Log.i(LOG_TAG, "TEST: refreshUIWith() is called...")

        // try to touch View of UI thread
        this@WondersListActivity.runOnUiThread(java.lang.Runnable {
            val wondersList = rvWondersList
            val layoutManager = LinearLayoutManager(this)
            wondersList.layoutManager = layoutManager
            wondersList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            val adapter = WondersAdapters(this@WondersListActivity, wondersEntity)
            wondersList.adapter = adapter
        })
    }

    private fun isNetworkAvailable(): Boolean {
        Log.i(LOG_TAG, "TEST: isNetworkAvailable() is called...")

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo

        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun progressBarLoading() {
        Log.i(LOG_TAG, "TEST: progressBarLoading() is called...")

        // when the task is started, make progressBar is loading
        this@WondersListActivity.runOnUiThread(java.lang.Runnable {
            progressBar?.visibility = View.GONE
        })
    }

    private fun progressBarGone() {
        Log.i(LOG_TAG, "TEST: progressBarLoading() is called...")

        // when the task is completed, make progressBar gone
        this@WondersListActivity.runOnUiThread(java.lang.Runnable {
            progressBar?.visibility = View.GONE
        })
    }
}
