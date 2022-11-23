package com.example.todolist

//import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mText: CharSequence = "Loading Notes..."
    private var mIndex = 0
    private var mDelay: Long = 50
    private lateinit var mHandler: Handler
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

                    mHandler = Handler()
                    mHandler.removeCallbacks(characterAdder)
                    mHandler.postDelayed(characterAdder, mDelay)

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

    private val characterAdder: Runnable = object : Runnable {
        override fun run() {
            binding.textView2.text = mText.subSequence(0, mIndex++)
            if (mIndex <= mText.length) {
                mHandler.postDelayed(this, mDelay)
            }
        }
    }
}