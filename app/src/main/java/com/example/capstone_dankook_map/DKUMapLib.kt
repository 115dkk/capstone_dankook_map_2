package com.example.capstone_dankook_map

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DKLib::class], version = 1, exportSchema = false)
abstract class DKUMapLibDB :RoomDatabase() {
    abstract fun dklibDAO(): DKLibDAO
}