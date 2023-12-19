package com.example.promorder.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nameorder") val nameproduct: String,
    @ColumnInfo(name = "countorder") val countproduct: String,
    @ColumnInfo(name = "priceorder") val priceproduct: String,
    @ColumnInfo(name = "statusorder") val statusorder: String,
    @ColumnInfo(name = "iduserord") val iduserord: String,
    )
