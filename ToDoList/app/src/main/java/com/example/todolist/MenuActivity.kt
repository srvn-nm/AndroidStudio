package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var binding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}