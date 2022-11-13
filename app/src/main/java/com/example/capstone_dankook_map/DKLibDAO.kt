package com.example.capstone_dankook_map

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface DKLibDAO {
    @Query("SELECT * FROM DKULibFloor")
    suspend fun getAll(): List<DKLib>

    @Delete
    suspend fun delete(DKULibFloor: DKLib)
}