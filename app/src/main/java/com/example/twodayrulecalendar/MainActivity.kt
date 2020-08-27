package com.example.twodayrulecalendar

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.timessquare.CalendarPickerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateToday = Date()
        val calendar : CalendarView = findViewById(R.id.calendar_grid)

        textView = findViewById(R.id.tv)

        fab_btn.setOnClickListener{
            showDatePicker()
        }
    }

    fun showDatePicker(){
        var dpd: DatePickerDialog = DatePickerDialog(
            this,this,  Calendar.getInstance().get(Calendar.YEAR)
            ,Calendar.getInstance().get(Calendar.MONTH)
            , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
        dpd.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val date: String = "dd/mm/yy $day/$month/$year"
        textView?.text = date
    }
}

