package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMenuBinding
    private lateinit var userIdList:ArrayList<Int>
    private lateinit var taskIdList:ArrayList<Int>
    private lateinit var taskTextList:ArrayList<String>
    private lateinit var taskStateList:ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        userIdList = intent.getIntegerArrayListExtra("userIdList") as ArrayList<Int>
        taskIdList = intent.getIntegerArrayListExtra("taskIdList") as ArrayList<Int>
        taskTextList = intent.getStringArrayListExtra("taskTextList") as ArrayList<String>
        taskStateList = intent.getStringArrayListExtra("taskStateList") as ArrayList<String>
        setContentView(binding.root)
    }

    fun userId(view: View){
        binding.button.visibility=View.GONE
        binding.button2.visibility=View.GONE
        binding.button3.visibility=View.GONE
        binding.searchLayout.visibility=View.VISIBLE
    }

    fun searchButton(view: View){
        val userInfo = binding.textInputLayout.editText?.text?.toString()
        var check=true
        while(check){
            if (userInfo != null) {
                if (userInfo.toInt() in userIdList || userInfo.toInt() in taskIdList || userInfo in taskTextList){
                    check=false
                    val intent = Intent(this , ToDoListActivity::class.java)
                    intent.putExtra("userInfo",userInfo)
                    intent.putExtra("userIdList",userIdList)
                    intent.putExtra("taskIdList",taskIdList)
                    intent.putExtra("taskTextList",taskTextList)
                    intent.putExtra("taskStateList",taskStateList)
                    binding.button.visibility=View.VISIBLE
                    binding.button2.visibility=View.VISIBLE
                    binding.button3.visibility=View.VISIBLE
                    binding.searchLayout.visibility=View.GONE
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Input Wrong!",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}