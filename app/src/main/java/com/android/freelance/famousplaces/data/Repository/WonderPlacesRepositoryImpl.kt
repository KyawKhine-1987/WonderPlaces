package com.android.freelance.famousplaces.data.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.android.freelance.famousplaces.data.db.entity.WonderPlacesDao
import com.android.freelance.famousplaces.data.db.entity.WonderPlacesEntry
import com.android.freelance.famousplaces.data.db.entity.Wonders
import com.android.freelance.famousplaces.data.network.WonderPlacesNetworkDataSource
import com.android.freelance.famousplaces.data.network.response.WondersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WonderPlacesRepositoryImpl(
    private val wonderPlacesDao: WonderPlacesDao,
    private val wonderPlacesNetworkDataSource: WonderPlacesNetworkDataSource
) : WonderPlacesRepository {

    private val LOG_TAG = WonderPlacesRepositoryImpl::class.java.name

    init {
        wonderPlacesNetworkDataSource.downloadedWonderPlaces.observeForever { newWonderPlaces ->
            persistFetchedWonderPlaces(newWonderPlaces)
        }
    }

    //private fun persistFetchedWonderPlaces(newWonderPlaces: WondersResponse?)
    private fun persistFetchedWonderPlaces(fetchedWonderPlaces: WondersResponse) {
        Log.i(LOG_TAG, "TEST: persistFetchedWonderPlaces() called...")

        GlobalScope.launch(Dispatchers.IO) {
            wonderPlacesDao.insert(fetchedWonderPlaces.wonders)
        }
    }


    // related from WonderPlacesRepository Interface.
    override suspend fun getWonderPlacesList(): LiveData<List<WonderPlacesEntry>> {
        Log.i(LOG_TAG, "TEST: getWonderPlacesList() called...")

        return withContext(Dispatchers.IO) {
           /* return@withContext wonderPlacesDao.getAllLocation()*/
            return@withContext wonderPlacesDao.getAllData()
        }
    }
}