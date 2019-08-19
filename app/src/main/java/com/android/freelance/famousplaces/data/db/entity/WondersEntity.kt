package com.android.freelance.famousplaces.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val wonder_ID = 0

@Entity(tableName = "tbl_wonders")
data class WondersEntity(

    @ColumnInfo(name = "location")
    var location: String ?= null, //  var solve this error "Cannot find setter for field."

    @ColumnInfo(name = "description")
    var description: String ?= null,

    @ColumnInfo(name = "image_url")
    var image: String ?= null,

    @ColumnInfo(name = "latitude")
    var lat: Double = 0.00,

    @ColumnInfo(name = "longitude")
    var long: Double  = 0.00
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = wonder_ID
}