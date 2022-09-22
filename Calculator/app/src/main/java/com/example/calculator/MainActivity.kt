package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    fun OnDigitClick(view:View){
        val clickedButton = view as Button
        binding.textView.append(clickedButton.text)

    }

    fun ClearAll(view: View){
        binding.textView.text = ""
    }

    fun ClearBack(view: View){

        val Fakedisplay = view as Button
        var str: String = Fakedisplay.text as String
        if (str.length > 1) {
            str = str.substring(0, str.length - 1)
            Fakedisplay.text = str
        } else if (str.length <= 1) {
            Fakedisplay.text = "0"
        }

    }
}