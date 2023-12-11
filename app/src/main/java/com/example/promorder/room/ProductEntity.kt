package com.example.promorder.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
@PrimaryKey(true) val id: String,
@ColumnInfo(name = "nameproduct") val nameproduct: String,
@ColumnInfo(name = "countproduct") val countproduct: String,
@ColumnInfo(name = "priceproduct") val priceproduct: String,
)
