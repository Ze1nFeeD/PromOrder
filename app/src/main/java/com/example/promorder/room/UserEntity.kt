package com.example.promorder.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(false) val name: String,
    @ColumnInfo(name = "ogrn") val ogrn: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "nameorg") val nameorg: String,
    @ColumnInfo(name = "inn") val inn: String,
    @ColumnInfo(name = "role") val role: Int,
)