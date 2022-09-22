package com.example.myfirstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val B = findViewById<Button>(R.id.buttonClickOnME)
        B.setOnClickListener{
            Toast.makeText(this,"Sarvin Nami",Toast.LENGTH_LONG).show()
        }
    }
}