package com.example.capstone_dankook_map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4
@Entity(tableName = "DKULibFloor")
data class DKLib(
    @PrimaryKey @ColumnInfo(name = "rowid") val fid: Int?,
    @ColumnInfo(name = "Floor1") val field1: String?,
    @ColumnInfo(name = "Floor2") val field2: String?,
    @ColumnInfo(name = "Floor3") val field3: String?,
    @ColumnInfo(name = "Floor4") val field4: String?,
    @ColumnInfo(name = "Floor5") val field5: String?,
    @ColumnInfo(name = "Floor6") val field6: String?
)
