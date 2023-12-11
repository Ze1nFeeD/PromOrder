package com.example.promorder.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface ProductDao {
    //    @Query("SELECT * FROM users")
//    fun getAllUsers(): List<UserEntity>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProduct(userEntity: UserEntity)
}