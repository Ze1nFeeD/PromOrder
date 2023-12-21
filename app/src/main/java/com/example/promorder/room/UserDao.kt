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

@Query("SELECT * FROM users WHERE role=1")
fun selectCLient():List<UserEntity>
@Query("UPDATE users SET password =:newpass WHERE name=:name")
fun resPass(name: String,newpass: String): Int

}