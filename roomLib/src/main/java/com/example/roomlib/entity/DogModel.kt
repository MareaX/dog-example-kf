package com.example.roomlib.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.roomlib.config.RoomConfig.Dogs.Companion.DOG_TABLE

@Entity(tableName = DOG_TABLE, indices = [Index(value = ["dogName", "description"], unique = true)])
data class DogModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dogName: String? = "",
    val description: String? = "",
    val age: Int,
    val imageUrl: String? = ""
)