package com.example.mycalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var isLastInputNumber = false
    var isDotInputInText = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

    }

    fun onDigitClick(view:View){
        isLastInputNumber = true
        val clickedButton = view as Button
        binding.textView.append(clickedButton.text)

    }

    fun clearAll(view: View){
        binding.textView.text = ""
        isDotInputInText = false
        isLastInputNumber =false
    }

    fun clearBack(view: View){
        var str = binding.textView.text.toString()
        val lastInput = str[str.length - 1]
        if (lastInput == '.'){
            isDotInputInText = false
        }
        if (str.length > 1) {
            str = str.substring(0, str.length - 1)
            binding.textView.text = str
        } else if (str.length <= 1) {
            binding.textView.text = ""
        }
        else if (str.isEmpty()){
            binding.textView.text = ""
        }
    }

    fun onDecimalPoint(view: View){
        if (isLastInputNumber && !isDotInputInText){
            val clickedButton = view as Button
            binding.textView.append(clickedButton.text)
            isDotInputInText = true
            isLastInputNumber = false
        }
    }

    fun onOperator(view: View){
        val button = view as Button
        if (isLastInputNumber && !isOperatorInText(binding.textView.text.toString())){
            binding.textView.append(button.text)
            isDotInputInText = false
            isLastInputNumber = false
        }
    }

    private fun isOperatorInText(text:String):Boolean {
        return if (text.startsWith("-")) {
            false
        } else {
            text.contains("+") || text.contains("-") || text.contains("×") || text.contains("÷") || text.contains("\"@\"") || text.contains("%")
        }
    }

    fun onEqualClick(view: View){
        var prefix = ""
        if (!isLastInputNumber) return

        var inputValue = binding.textView.text.toString()

        if(inputValue.contains("÷")){
            val splitValue = inputValue.split("÷")
            binding.textView.text = (splitValue[0].toDouble() / splitValue[1].toDouble()).toString()
        }
        if(inputValue.contains("×")){
            val splitValue = inputValue.split("×")
            binding.textView.text = (splitValue[0].toDouble() * splitValue[1].toDouble()).toString()
        }
        if(inputValue.contains("+")){
            val splitValue = inputValue.split("+")
            binding.textView.text = (splitValue[0].toDouble() + splitValue[1].toDouble()).toString()
        }
        if(inputValue.contains("\"@\"")){
            val splitValue = inputValue.split("\"@\"")
            binding.textView.text = (splitValue[0].toDouble() * splitValue[1].toDouble() / 100).toString()
        }
        if(inputValue.contains("%")){
            val splitValue = inputValue.split("%")
            binding.textView.text = (splitValue[0].toDouble() % splitValue[1].toDouble()).toString()
        }

        if(inputValue.startsWith("-")){
            prefix = "-"
            inputValue = inputValue.substring(1)
        }

        if(inputValue.contains("-")){
            val splitValue = inputValue.split("-")
            var firstNumber = splitValue[0]
            if (prefix.isNotEmpty()){
                firstNumber = prefix + splitValue[0]
            }
            binding.textView.text = (firstNumber.toDouble() - splitValue[1].toDouble()).toString()
        }
    }
}