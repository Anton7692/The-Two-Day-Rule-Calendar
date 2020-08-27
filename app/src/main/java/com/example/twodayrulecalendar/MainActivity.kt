package com.example.twodayrulecalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.CalendarView
import android.widget.Toast
import com.squareup.timessquare.CalendarPickerView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateToday = Date()
        val calendar : CalendarView = findViewById(R.id.calendar_grid)

        calendar.setOnDateChangeListener { calendarView, y, m, d ->
            Toast.makeText(this, "$y, $m, $d", Toast.LENGTH_SHORT).show()
        }
    }
}