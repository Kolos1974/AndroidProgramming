package com.example.andwallet.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trans")
data class Trans(
    @PrimaryKey(autoGenerate = true) val transId: Long?,
    @ColumnInfo(name = "detail") var detail: String,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "sign") var sign: Short
)
