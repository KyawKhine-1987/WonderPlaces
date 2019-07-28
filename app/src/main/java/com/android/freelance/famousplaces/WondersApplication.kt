package com.android.freelance.famousplaces

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import android.app.Application
import android.util.Log
import com.android.freelance.famousplaces.data.Repository.WonderPlacesRepository
import com.android.freelance.famousplaces.data.Repository.WonderPlacesRepositoryImpl
import com.android.freelance.famousplaces.data.db.entity.WondersDatabase
import com.android.freelance.famousplaces.data.network.ConnectivityInterceptorImpl
import com.android.freelance.famousplaces.data.network.WonderPlacesApiService
import com.android.freelance.famousplaces.data.network.WonderPlacesNetworkDataSource
import com.android.freelance.famousplaces.data.network.WonderPlacesNetworkDataSourceImpl
import com.android.freelance.famousplaces.data.network.response.ConnectivityInterceptor
import com.android.freelance.famousplaces.ui.list.WonderPlacesListViewModelFactory
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class WondersApplication : Application(), KodeinAware {

    private val LOG_TAG = WondersApplication::class.java.name

    override val kodein = Kodein.lazy {

        import (androidXModule(this@WondersApplication))

        bind() from singleton { WondersDatabase(instance()) }
        bind() from singleton { instance<WondersDatabase>().wonderPlacesDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WonderPlacesApiService(instance()) }
        bind<WonderPlacesNetworkDataSource>() with singleton { WonderPlacesNetworkDataSourceImpl(instance()) }
        bind<WonderPlacesRepository>() with singleton { WonderPlacesRepositoryImpl(instance(), instance()) }
        bind() from singleton { WonderPlacesListViewModelFactory(instance()) }
    }

    override fun onCreate() {
        Log.i(LOG_TAG, "TEST: onCreate() called...")

        super.onCreate()
    }
}