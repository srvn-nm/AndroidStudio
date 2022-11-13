package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val client = OkHttpClient()
        val request = Request.Builder().url("https://jsonplaceholder.typicode.com/todos").build()
    }
}