package com.android.freelance.famousplaces.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val wonder_ID = 0

@Entity(tableName = "tbl_wonders")
data class Wonders(
    @SerializedName("location")
    val location: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("lat")
    val lat: Double,

    @SerializedName("long")
    val long: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = wonder_ID
}