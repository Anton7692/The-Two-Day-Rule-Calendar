package com.example.twodayrulecalendar

import android.app.ActionBar
import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.timessquare.CalendarPickerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@Suppress("PLUGIN_WARNING")
class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateToday = Date()
        val calendar : CalendarView = findViewById(R.id.calendar_grid)


        //TODO add todos (text views) programmatically on every date pick
        fab_btn.setOnClickListener{
            showDatePicker()
        }
    }

    private fun showDatePicker(){
        val dpd: DatePickerDialog = DatePickerDialog(
            this,this,  Calendar.getInstance().get(Calendar.YEAR)
            ,Calendar.getInstance().get(Calendar.MONTH)
            , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
        dpd.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val date: String = "dd/mm/yy $day/$month/$year"
        val newTv: TextView = TextView(this)
        newTv.textSize = 20f
        newTv.setTextColor(Color.parseColor("#000000"))
        newTv.text = date
        newTv.layoutParams = ViewGroup.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        val ll: LinearLayout = findViewById(R.id.ll_for_todos)
        ll.addView(newTv)
    }
}

