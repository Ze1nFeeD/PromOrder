package com.example.promorder.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProduct(productEntity: ProductEntity)
    @Query("SELECT * FROM products")
    fun selectProducts():List<ProductEntity>

}