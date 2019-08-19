package com.android.freelance.famousplaces.data.db.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WondersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wonder: ArrayList<WondersEntity>)

    @Query("select * from tbl_wonders nolock order by id asc;")
    fun getAllData(): LiveData<List<WondersEntity>>

    @Query("delete from tbl_wonders;")
    fun deleteAll()

    /*@Query("select * from tbl_wonders where id = $wonder_ID")*/
    /* @Query("select location from tbl_wonders nolock;")
     fun getAllLocation(): LiveData<WonderPlacesEntry>*/
}