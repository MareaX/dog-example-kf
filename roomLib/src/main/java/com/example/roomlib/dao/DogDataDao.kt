package com.example.roomlib.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomlib.entity.DogModel

@Dao
interface DogDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<DogModel>)

    @Query("select * from tbl_dog")
    fun fetchItems(): List<DogModel>
}
