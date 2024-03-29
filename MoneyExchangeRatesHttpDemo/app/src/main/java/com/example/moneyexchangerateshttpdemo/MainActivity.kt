package com.example.moneyexchangerateshttpdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moneyexchangerateshttpdemo.data.MoneyResult
import com.example.moneyexchangerateshttpdemo.network.MoneyAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val moneyAPI = retrofit.create(MoneyAPI::class.java)

        btnGetRates.setOnClickListener {
            val moneyCall = moneyAPI.getMoney("USD")
            // API key problem!!
            // val moneyCall = moneyAPI.getSymbols("pQCenLMjRXNLcbshVZRSFdrMne9MUaAB")
            // val moneyCall = moneyAPI.getMoney2("USD")

            moneyCall.enqueue(object: Callback<MoneyResult> {
                override fun onFailure(call: Call<MoneyResult>, t: Throwable) {
                    tvData.text = t.message
                }

                override fun onResponse(call: Call<MoneyResult>, response: Response<MoneyResult>) {
                    val moneyResult = response.body()

                    tvData.text = "HUF: ${moneyResult?.rates?.HUF}"
                }
            })
        }
    }
}