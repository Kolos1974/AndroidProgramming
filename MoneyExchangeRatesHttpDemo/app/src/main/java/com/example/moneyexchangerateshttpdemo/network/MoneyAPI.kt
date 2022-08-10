package com.example.moneyexchangerateshttpdemo.network

import com.example.moneyexchangerateshttpdemo.data.MoneyResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


// URL: https://api.exchangeratesapi.io/latest?base=USD

// HOST: https://api.exchangeratesapi.io
//
// PATH: /latest
//
// QUERY arguments: ?   base=EUR

interface MoneyAPI {

    // @GET("/latest")
    @GET("/v1/latest")
    fun getMoney(@Query("base") base: String) : Call<MoneyResult>

    @GET("/v1/symbols")
    fun getSymbols(@Query("access_key") access_key: String) : Call<MoneyResult>

    @GET("/v1/latest")
    fun getMoney2(@Query("access_key") access_key: String) : Call<MoneyResult>

    @GET("/v1/latest")
    fun getMoreParameter(@Query("param1") param1: String, @Query("param2") param2: String) : Call<MoneyResult>
}