package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val client = OkHttpClient()
        val request = Request.Builder().url("https://jsonplaceholder.typicode.com/todos").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity,"Oh oh!",Toast.LENGTH_SHORT).show()
                Log.d("tagJsonWrong" , "Json: not found")
            }
            override fun onResponse(call: Call, response: Response) {
                val rawJSONContext = response.body.string()
                Log.d("tagJson" , "Json: $rawJSONContext")
                val jsonArray = JSONArray(rawJSONContext)

                runOnUiThread {

                    jsonToDoList(jsonArray)

                }

            }

        })
    }

    private fun jsonToDoList(jsonArray:JSONArray){
        val userIdList = ArrayList<Int>()
        val taskIdList = ArrayList<Int>()
        val taskTextList = ArrayList<String>()
        val taskStateList = ArrayList<String>()

        var index = 0
        while(index<jsonArray.length()){

            val tempJSONObject = jsonArray.getJSONObject(index)

            userIdList.add(tempJSONObject.getInt("userId"))
            taskIdList.add(tempJSONObject.getInt("id"))
            taskTextList.add(tempJSONObject.getString("title"))
            taskStateList.add(tempJSONObject.getBoolean("completed").toString())
            index ++
        }
//        val intent = Intent(this@MainActivity , MenuActivity::class.java)
//
//        intent.putExtra("userIdList",userIdList)
//        intent.putExtra("taskIdList",taskIdList)
//        intent.putExtra("taskTextList",taskTextList)
//        intent.putExtra("taskStateList",taskStateList)
//        startActivity(intent)
    }
}