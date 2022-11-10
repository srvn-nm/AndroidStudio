package com.example.meteorology

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.meteorology.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if (binding.textInputLayout.editText.toString().isEmpty()){
                Toast.makeText(this,"Enter city name,first!",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,MainActivity2::class.java)
                intent.putExtra("cityName",binding.textInputLayout.editText.toString())
                startActivity(intent)
            }
        }
    }

}