package com.example.roomlib.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomlib.config.RoomConfig.Companion.VERSION_DATABASE
import com.example.roomlib.dao.DogDataDao
import com.example.roomlib.entity.DogModel

@Database(entities = [DogModel::class], version = VERSION_DATABASE,  exportSchema = false)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDatabaseDao(): DogDataDao
}