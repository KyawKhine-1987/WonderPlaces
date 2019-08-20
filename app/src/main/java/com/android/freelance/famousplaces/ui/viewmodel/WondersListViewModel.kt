package com.android.freelance.famousplaces.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.freelance.famousplaces.data.Repository.WondersRepository
import com.android.freelance.famousplaces.data.db.entity.WondersEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WondersListViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val LOG_TAG = WondersListViewModel::class.java.name

    // Coroutine Implementation Code lines
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    private val scope = CoroutineScope(coroutineContext)

    private var wondersRepository: WondersRepository? = null

    init {
        wondersRepository = WondersRepository(application)
    }

    private fun cancelParentJob() = parentJob.cancel()

    override fun onCleared() {
        Log.i(LOG_TAG, "TEST: onCleared() is called...")

        super.onCleared()
        cancelParentJob()
    }

    //suspend
    fun insert(wondersEntity: ArrayList<WondersEntity>) = scope.launch(Dispatchers.IO) {
        Log.i(LOG_TAG, "TEST: insert() is called...")

        wondersRepository!!.insert(wondersEntity)
    }

    val fetchAllWonders: LiveData<List<WondersEntity>> = wondersRepository!!.fetchAllWonders

    //suspend
    fun deleteAllData() = scope.launch(Dispatchers.IO) {
        Log.i(LOG_TAG, "TEST: deleteAllData() is called...")

        wondersRepository!!.deleteAllData()
    }

    /*suspend fun fetchAllWonders(): LiveData<List<WondersEntity>> {
        Log.i(LOG_TAG, "TEST: fetchAllWonders() is called...")

       return wondersRepository!!.fetchAllWonders()
    }*/
}