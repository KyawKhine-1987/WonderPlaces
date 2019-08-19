package com.android.freelance.famousplaces.data.db.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [WondersEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WondersDatabase : RoomDatabase() {

    abstract fun wondersDao(): WondersDao

    companion object {
        @Volatile
        private var instance: WondersDatabase? = null
        private val LOCK = Any()// LOCK is dummy object.

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                WondersDatabase::class.java, "Wonders")
                .build()
    }
}