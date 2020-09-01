package com.example.twodayrulecalendar

import android.app.ActionBar
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.marginRight
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.squareup.timessquare.CalendarPickerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dialog.view.*
import java.util.*
import androidx.core.view.marginLeft as marginLeft
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var drawerLayout: DrawerLayout? = null
    var navView: NavigationView? = null
    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        navView!!.bringToFront()
        setSupportActionBar(toolbar)
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar ,R.string.navigation_closed, R.string.navigation_open)
        drawerLayout!!.addDrawerListener(toggle)

        toggle.syncState()
        navView!!.setNavigationItemSelectedListener{
            onNavigationItemSelect(it)
        }


        val pBar: ProgressBar = findViewById(R.id.progress)
        setProgressbar(pBar)
    }

    private fun onNavigationItemSelect(it: MenuItem) : Boolean{

        if (it.itemId == R.id.nav_add){
            showDatePicker()
        }

        return true
    }

    private fun showDatePicker(){
        val dpd: DatePickerDialog = DatePickerDialog(
            this,this,  Calendar.getInstance().get(Calendar.YEAR)
            ,Calendar.getInstance().get(Calendar.MONTH)
            , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
        dpd.show()

        //todo maybe remove
        drawerLayout!!.closeDrawer(GravityCompat.START)
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

    //TODO make it so progress bar displays accurate data --b
    private fun setProgressbar(pb: ProgressBar): ProgressBar{
        return pb
    }
}

