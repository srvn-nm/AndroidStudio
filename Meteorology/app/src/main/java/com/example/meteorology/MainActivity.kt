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
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val client = OkHttpClient()

        val request = Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?q=tehran&appid=501b25848afaeca041fb1ce35525d09b").build()

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

                Log.d("tagx","onResponse: successful")

                runOnUiThread {
                    showInfo(downloadedJSON.getString("name"),weatherCity,weatherImageUrl,timeFromUnix(sunrise),timeFromUnix(sunset))

                }

            }

        })
    }

    fun showInfo(city: String, weather:String, imageUrl:String, sunrise:String, sunset:String){
        binding.city.text = city
        binding.weather.text = weather
        Glide.with(this@MainActivity).load(imageUrl).into(binding.weatherIcon)
        binding.sunrise.text = sunrise
        binding.sunset.text = sunset
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun timeFromUnix(unixTime:Int): String {
        val time = unixTime*1000.toLong()
        val date = Date(time)
        return SimpleDateFormat("hh:mm:ss").format(date)
    }
}