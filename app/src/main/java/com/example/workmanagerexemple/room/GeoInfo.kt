package com.example.workmanagerexemple.room

import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME="geo_info"
@Entity(tableName = TABLE_NAME)
data class GeoInfo (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val latitude:Double,
    val longitude:Double,
    val date: Long
)