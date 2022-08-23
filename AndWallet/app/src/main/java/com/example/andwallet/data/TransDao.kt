package com.example.andwallet.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransDAO {

    @Query("SELECT * FROM trans")
    fun getAllTrans(): List<Trans>

    @Query("SELECT * FROM trans WHERE sign = :sign")
    fun getSomeTrans(sign: Int): List<Trans>

    @Insert
    fun insertTrans(vararg trans: Trans)

    @Delete
    fun deleteTrans(trans: Trans)

    @Query("DELETE FROM trans")
    fun deleteAllTrans()

}