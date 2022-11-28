package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityToDoListBinding

class ToDoListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityToDoListBinding
    private lateinit var userIdList:ArrayList<Int>
    private lateinit var taskIdList:ArrayList<Int>
    private lateinit var taskTextList:ArrayList<String>
    private lateinit var taskStateList:ArrayList<String>
    private lateinit var userInfo:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityToDoListBinding.inflate(layoutInflater)
        userIdList = intent.getIntegerArrayListExtra("userIdList") as ArrayList<Int>
        taskIdList = intent.getIntegerArrayListExtra("taskIdList") as ArrayList<Int>
        taskTextList = intent.getStringArrayListExtra("taskTextList") as ArrayList<String>
        taskStateList = intent.getStringArrayListExtra("taskStateList") as ArrayList<String>
        userInfo = intent.getStringExtra("userInfo") as String
        setContentView(binding.root)
    }
}