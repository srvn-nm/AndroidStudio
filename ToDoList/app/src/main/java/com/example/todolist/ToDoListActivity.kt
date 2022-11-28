package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityToDoListBinding

class ToDoListActivity : AppCompatActivity() {
    lateinit var binding : ActivityToDoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}