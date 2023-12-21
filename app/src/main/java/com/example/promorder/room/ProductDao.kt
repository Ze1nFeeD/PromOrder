package com.example.promorder.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProduct(productEntity: ProductEntity)
    @Query("SELECT * FROM products")
    fun selectProducts():List<ProductEntity>
@Query("DELETE  FROM products WHERE id=:id")
suspend fun deleteProd(id: String): Int

@Query("UPDATE products SET nameproduct =:nameproduct, countproduct =:countprod,priceproduct =:priceprod WHERE id = :idprod")
suspend fun updateProd(nameproduct: String,countprod: String, priceprod: String, idprod: Int): Int
    @Query("UPDATE products SET countproduct =:countproduct WHERE id = :idprod")
    suspend fun updateCountProd(countproduct: String, idprod: Int): Int
}