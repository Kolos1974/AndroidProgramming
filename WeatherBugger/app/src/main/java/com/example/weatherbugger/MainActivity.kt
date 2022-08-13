package com.example.weatherbugger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.weatherbugger.data.WeatherResult
import com.example.weatherbugger.network.WeatherAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    companion object {
        const val API_KEY = "6bd095eb87ff1527462959a97168982f"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var weatherAPI = retrofit.create(WeatherAPI::class.java)

        btnGet.setOnClickListener {

            val call = weatherAPI.getWeatherDetails(
                etCity.text.toString(),
                "metric",
                API_KEY
            )


            //with(call) {
            call.enqueue(object : Callback<WeatherResult> {
                override fun onResponse(
                    call: Call<WeatherResult>,
                    response: Response<WeatherResult>
                ) {
                    val weatherResult = response.body()

                    //tvData.text = " ${weatherResult?.main?.temp} Celsius"
                    // tvData.text = "  Celsius"
                    tvCoordinates.text = "Coordinates: ${weatherResult?.coord?.lon}, ${weatherResult?.coord?.lat}"
                    tvWeather.text = "Weather: ${weatherResult?.weather?.get(0)?.main}"
                    tvDescription.text = "Description: ${weatherResult?.weather?.get(0)?.description}"
                    tvTemperature.text = "Temperature: ${weatherResult?.main?.temp}"
                    tvPressure.text = "Pressure: ${weatherResult?.main?.pressure}"
                    tvHumidity.text = "Temperature: ${weatherResult?.main?.humidity}"
                    tvMinimumTemperature.text = "Minimum temperature: ${weatherResult?.main?.temp_min}"
                    tvMaximumTemperature.text = "Maximum temperature: ${weatherResult?.main?.temp_max}"


                    Glide.with(this@MainActivity)
                        .load(
                            ("https://openweathermap.org/img/w/" +
                                    response.body()?.weather?.get(0)?.icon
                                    + ".png")
                        )
                        .into(ivWeather)
                }

                override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                    tvCoordinates.text = t.message
                }

            })
            //}

        }


    }
}