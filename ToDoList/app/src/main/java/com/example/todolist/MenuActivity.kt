package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding
    private lateinit var userIdList:ArrayList<Int>
    private lateinit var taskIdList:ArrayList<Int>
    private lateinit var taskTextList:ArrayList<String>
    private lateinit var taskStateList:ArrayList<String>
//    val toDoListIntent = Intent(this , ToDoListActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        userIdList = intent.getIntegerArrayListExtra("userIdList") as ArrayList<Int>
        taskIdList = intent.getIntegerArrayListExtra("taskIdList") as ArrayList<Int>
        taskTextList = intent.getStringArrayListExtra("taskTextList") as ArrayList<String>
        taskStateList = intent.getStringArrayListExtra("taskStateList") as ArrayList<String>
        setContentView(binding.root)
    }
}