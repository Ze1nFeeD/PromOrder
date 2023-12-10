package com.example.promorder.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class RoomDb: RoomDatabase() {
    abstract fun userDao(): UserDao}

