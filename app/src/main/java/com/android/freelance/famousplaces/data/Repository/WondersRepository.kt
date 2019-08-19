package com.android.freelance.famousplaces.data.Repository

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.android.freelance.famousplaces.data.db.entity.WondersDao
import com.android.freelance.famousplaces.data.db.entity.WondersDatabase
import com.android.freelance.famousplaces.data.db.entity.WondersEntity
import com.android.freelance.famousplaces.data.network.response.WondersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WondersRepository(application: Application) {

    private val LOG_TAG = WondersRepository::class.java.name

    private var wondersDao: WondersDao? = null

    init {
        //createDatabase()
        val db = WondersDatabase.invoke(application)
        wondersDao = db.wondersDao()

    }

    //private fun persistFetchedWonderPlaces(newWonderPlaces: WondersResponse?)
    //suspend
    @WorkerThread
    fun insert(wondersEntity: ArrayList<WondersEntity>) {
        Log.i(LOG_TAG, "TEST: insert() called...")

        wondersDao!!.insert(wondersEntity)
        /*GlobalScope.launch(Dispatchers.IO) {
            wondersDao!!.insert(wondersEntity)
        }*/
    }

    // related from WonderPlacesRepository Interface.
    /*suspend fun fetchAllWonders(): LiveData<List<WondersEntity>> {
        Log.i(LOG_TAG, "TEST: fetchAllWonders() called...")

        return withContext(Dispatchers.IO) {
            return@withContext wondersDao!!.getAllData()
        }
    }*/
    val fetchAllWonders: LiveData<List<WondersEntity>> = wondersDao!!.getAllData()

    //suspend
    fun deleteAllData() {
        Log.i(LOG_TAG, "TEST: deleteAllData() called...")

        wondersDao!!.deleteAll()
        /*GlobalScope.launch(Dispatchers.IO) {
            wondersDao!!.deleteAll()
        }*/
    }
}