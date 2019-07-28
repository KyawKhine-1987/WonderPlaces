package com.android.freelance.famousplaces.data.db.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WonderPlacesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wonderPlacesEntries: List<Wonders>)

    /*@Query("select * from tbl_wonders where id = $wonder_ID")*/
    @Query("select location from tbl_wonders nolock;")
    fun getAllLocation(): LiveData<WonderPlacesEntry>

    @Query("Select * From tbl_wonders Order By id ASC;")
    fun getAllData(): LiveData<List<WonderPlacesEntry>>

    @Query("Delete From tbl_wonders;")
    fun deleteAll()

    /*@Insert
    fun add(word: Word)*/
}