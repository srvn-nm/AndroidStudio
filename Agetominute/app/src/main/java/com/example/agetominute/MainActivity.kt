package com.example.agetominute

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonSelectDate)
        val textAgeInMinutes = findViewById<TextView>(R.id.textViewAgeInMinutes)
        val textSelectedDate = findViewById<TextView>(R.id.textViewSelectedDate)
        val textMode = findViewById<TextView>(R.id.textViewMode)

        button.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->  val date = "${dayOfMonth}/${month+1}/${year}"
               Toast.makeText(this, date,Toast.LENGTH_LONG).show()
                val dateFormatter = SimpleDateFormat("dd/mm/yyyy")
                val selectedDate = dateFormatter.parse(date)
                val ageInMinutes = (dateFormatter.parse(dateFormatter.format(System.currentTimeMillis())).time/60000) - (selectedDate.time/60000)
                textAgeInMinutes.text = ageInMinutes.toString()
                textSelectedDate.text = date
                textMode.text = when(ageInMinutes/(60*24*362)){
                    in 0..3 -> "انرژی و سرزندگی"
                    in 3..6 -> "بازیگوشی"
                    in 6..8 -> "تخیل"
                    in 9..11 -> "ابتکار و خلاقیت"
                    in 12..20 -> "شور و اشتیاق"
                    in 20..35 -> "همت و پشتکار"
                    in 35..50 -> "تعمق و ژرف اندیشی"
                    in 50..80 -> "سخاوت و خیرخواهی"
                    !in 0..80 -> "خردمندی و فضیلت"
                    else -> "not in range"
                }
            },year,month,day)
            datePicker.datePicker.maxDate = Date().time
            datePicker.show()
        }
    }
}