package com.example.promorder.room

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var database: RoomDb? = null

    fun getDatabase(context: Context): RoomDb {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                RoomDb::class.java, "main_db"
            ).build()
        }
        return database!!
    }
}