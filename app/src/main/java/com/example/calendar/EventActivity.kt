package com.example.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TimePicker

class EventActivity : AppCompatActivity() {
    private var eventsLocalDate: ArrayList<String> = ArrayList()
    private var eventsTime: ArrayList<String> = ArrayList()
    private var eventsText: ArrayList<String> = ArrayList()
    private var currentTime: String = ""
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        eventsLocalDate = intent.extras?.getStringArrayList("localDate")!!
        eventsTime = intent.extras?.getStringArrayList("time")!!
        eventsText = intent.extras?.getStringArrayList("text")!!
        currentTime = intent.extras?.getString("currentTime")!!
        year = intent.extras?.getInt("year")!!
        month = intent.extras?.getInt("month")!!
        day = intent.extras?.getInt("day")!!
    }

    fun saveButton(view: View) {
        val timePicker = findViewById<TimePicker>(R.id.simpleTimePicker)
        val string = timePicker.hour.toString() + ":" + timePicker.minute.toString()
        eventsTime.add(string)
        eventsText.add(findViewById<EditText>(R.id.editTextPersonName).text.toString())
        eventsLocalDate.add(currentTime)
        var myIntent = Intent(this, MainActivity::class.java)
        myIntent.putExtra("localDate", eventsLocalDate)
        myIntent.putExtra("time", eventsTime)
        myIntent.putExtra("text", eventsText)
        myIntent.putExtra("year", year)
        myIntent.putExtra("month", month)
        myIntent.putExtra("day", day)
        startActivity(myIntent)
        finish()
    }
    fun cancelButton(view: View) {
        finish()
    }
}