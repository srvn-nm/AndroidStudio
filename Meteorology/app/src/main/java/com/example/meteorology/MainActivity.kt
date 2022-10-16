package com.example.meteorology

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.meteorology.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var client:OkHttpClient
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        client = OkHttpClient()
        refreshData()

        val pullToRefresh = binding.pullToRefresh
        pullToRefresh.setOnRefreshListener {
            refreshData()
            pullToRefresh.isRefreshing = false
        }
    }

    private fun refreshData() {
        val request = Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?q=tehran&appid=501b25848afaeca041fb1ce35525d09b&units=metric").build()

        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d("tagy","onFailure: failed")
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call, response: Response) {
                val downloadedJSON = JSONObject(response.body.string())
                val weatherCity = downloadedJSON.getJSONArray("weather").getJSONObject(0).getString("description")
                val weatherIcon = downloadedJSON.getJSONArray("weather").getJSONObject(0).getString("icon")
                val weatherImageUrl = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png"
                val sunrise = downloadedJSON.getJSONObject("sys").getInt("sunrise")
                val sunset = downloadedJSON.getJSONObject("sys").getInt("sunset")
                val temp = downloadedJSON.getJSONObject("main").getDouble("temp")
                val feelsLike = downloadedJSON.getJSONObject("main").getDouble("feels_like")
                val tempMin = downloadedJSON.getJSONObject("main").getDouble("temp_min")
                val tempMax = downloadedJSON.getJSONObject("main").getDouble("temp_max")
                val pressure = downloadedJSON.getJSONObject("main").getInt("pressure")
                val humidity = downloadedJSON.getJSONObject("main").getInt("humidity")

                Log.d("tagx","onResponse: successful")

                runOnUiThread {
                    showInfo(downloadedJSON.getString("name"),weatherCity,weatherImageUrl,timeFromUnix(sunrise),timeFromUnix(sunset),temp,feelsLike,tempMin,tempMax,pressure,humidity)

                }

            }

        })
    }

    @SuppressLint("SetTextI18n")
    fun showInfo(city: String, weather:String, imageUrl:String, sunrise:String, sunset:String, temp:Double, feelsLike:Double,tempMin:Double,tempMax:Double,pressure:Int,humidity:Int){
        binding.city.text = city
        binding.weather.text = weather
        Glide.with(this@MainActivity).load(imageUrl).into(binding.weatherIcon)
        binding.sunrise.text = "Sunrise: $sunrise"
        binding.sunset.text = "Sunset: $sunset"
        binding.temp.text = "Temperature: $temp°"
        binding.feelsLike.text = "FeelsLike: $feelsLike°"
        binding.tempmin.text = "Minimum: $tempMin°"
        binding.tempmax.text = "Maximum: $tempMax°"
        binding.pressure.text = "Pressure: $pressure"
        binding.humidity.text = "Humidity: $humidity"
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun timeFromUnix(unixTime:Int): String {
        val time = unixTime*1000.toLong()
        val date = Date(time)
        return SimpleDateFormat("hh:mm:ss").format(date)
    }
}