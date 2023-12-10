package com.example.promorder.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
//    @Query("SELECT * FROM users")
//    fun getAllUsers(): List<UserEntity>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(userEntity: UserEntity)
@Query("SELECT * FROM users WHERE name=:username AND password=:password LIMIT 1")
fun select(username: String, password: String):List<UserEntity>
@Query("SELECT * FROM users")
fun selectProd():List<UserEntity>
}