package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (binding.editTextUsername.text.toString().isEmpty()){
                Toast.makeText(this,"Enter you username,first!",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("username",binding.editTextUsername.text.toString())
                startActivity(intent)
            }
        }
        binding.button.setOnKeyListener(View.OnKeyListener{v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                if (binding.editTextUsername.text.toString().isEmpty()){
                    Toast.makeText(this,"Enter you username,first!",Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(this,QuestionActivity::class.java)
                    intent.putExtra("username",binding.editTextUsername.text.toString())
                    startActivity(intent)
                }
                return@OnKeyListener true
            }
            false
        })
    }
}