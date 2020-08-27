package com.example.twodayrulecalendar

import android.app.ActionBar
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginRight
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.timessquare.CalendarPickerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dialog.view.*
import java.util.*
import androidx.core.view.marginLeft as marginLeft

@Suppress("PLUGIN_WARNING")
class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateToday = Date()
        val calendar : CalendarView = findViewById(R.id.calendar_grid)


        //TODO make so a text box pops up after the date picker to write your todo --boris

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

        displayDialog(date)
    }

    private fun setTextView(newTv: TextView, input: String, date: String): TextView{
        newTv.textSize = 20f
        newTv.setTextColor(Color.parseColor("#000000"))
        newTv.text = input + " / " + date

        //layout_width and layout_height --boris
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT)
        params.setMargins(100, 10, 0, 10)
        newTv.layoutParams = params

        return newTv
    }

    private fun displayDialog(date: String){
        val dialog = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null)
        val builder = AlertDialog.Builder(this).setView(dialog)
        val alertDialog = builder.show()

        dialog.submit_bt.setOnClickListener {
            alertDialog.dismiss()
            //Gets the text entered by the user --boris
            val activityEntered = dialog.edit_todo.text.toString()
            val newTv: TextView = TextView(this)
            val ll: LinearLayout = findViewById(R.id.ll_for_todos)
            ll.addView(setTextView(newTv = newTv, input = activityEntered, date= date))
        }
    }
}

