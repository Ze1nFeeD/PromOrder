package com.example.promorder.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertOrder(orderEntity: OrderEntity)
    @Query("SELECT * FROM orders")
    fun selectOrders():List<OrderEntity>
    @Query("SELECT * FROM orders WHERE statusorder ='В обработке'")
    fun selectCurOrd():List<OrderEntity>
    @Query("UPDATE orders SET statusorder =:status WHERE id = :idord")
    suspend fun updateOrd(status: String, idord: Int): Int

}