package com.example.calendar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.YearMonth


class MainActivity : AppCompatActivity(), CalendarInterface {
    private var cells: ArrayList<String> = ArrayList()
    private val events: MutableList<Event> = ArrayList()
    private var eventOnThatDay: MutableList<Event> = ArrayList()
    private var date = LocalDate.now();
    private var cellClicked = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (intent.extras != null) {
            val eventsLocalDate = intent.extras?.getStringArrayList("localDate")!!
            val eventsTime = intent.extras?.getStringArrayList("time")!!
            val eventsText = intent.extras?.getStringArrayList("text")!!
            date = LocalDate.of(intent.extras?.getInt("year")!!, intent.extras?.getInt("month")!!, intent.extras?.getInt("day")!!)
            for (i in 0 until eventsTime.size) {
                events.add(Event(eventsLocalDate[i], eventsTime[i], eventsText[i]))
            }
        }
        if (savedInstanceState != null) {
            date = LocalDate.of(savedInstanceState.getInt("year"), savedInstanceState.getInt("month"), savedInstanceState.getInt("day"))
        }
        generateBoard()
        generateEvent()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("year", date.year)
        outState.putInt("month", date.monthValue)
        outState.putInt("day", date.dayOfMonth)
        super.onSaveInstanceState(outState)
    }

    private fun generateBoard() {
        findViewById<TextView>(R.id.textView).text = date.month.toString() + " " + date.year
        var yearMonth = YearMonth.from(date)
        var daysInMonth = yearMonth.lengthOfMonth()
        var firstDay = date.withDayOfMonth(1)
        var dayOfWeek = firstDay.dayOfWeek.value
        var counter = 1
        cells.clear()
        for (i in 1..42) {
            if (i < dayOfWeek || counter > daysInMonth) {
                cells.add("")
            } else {
                cells.add(counter.toString())
                counter++
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 7)
        if (LocalDate.now().year == date.year && LocalDate.now().month == date.month) {
            recyclerView.adapter = CalendarAdapter(cells, LocalDate.now().dayOfMonth.toString(), this)
        } else {
            recyclerView.adapter = CalendarAdapter(cells, "-1", this)
        }

    }

    private fun generateEvent() {
        if (cellClicked != "") {
            eventOnThatDay.clear()
            for (i in 0 until events.size) {
                if (events[i].getDate() == LocalDate.of(date.year, date.monthValue, cellClicked.toInt()).toString()) {
                    eventOnThatDay.add(events[i])
                }
            }
        }
        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView2.layoutManager = LinearLayoutManager(this)
        recyclerView2.adapter = EventAdapter(eventOnThatDay, this)
    }

    fun nextButton(view: View) {
        date = date.plusMonths(1)
        cellClicked = ""
        generateBoard()
    }
    fun previousButton(view: View) {
        date = date.minusMonths(1)
        cellClicked = ""
        generateBoard()
    }

    override fun cellClick(cell: String) {
        cellClicked = cell
        generateEvent()
    }

    override fun dellEvent(text: String, time: String) {
        for (i in 0 until events.size) {
            if (events[i].getText() == text && events[i].getHour() == time && events[i].getDate() == LocalDate.of(date.year, date.monthValue, cellClicked.toInt()).toString()) {
                events.removeAt(i)
                break
            }
        }
        generateEvent()
    }

    fun addButton(view: View) {
        if (cellClicked != "") {
            var myIntent = Intent(this, EventActivity::class.java)
            val eventsLocalDate: ArrayList<String> = ArrayList()
            val eventsTime: ArrayList<String> = ArrayList()
            val eventsText: ArrayList<String> = ArrayList()
            for (i in 0 until events.size) {
                eventsLocalDate.add(events[i].getDate())
                eventsText.add(events[i].getText())
                eventsTime.add(events[i].getHour())
            }
            myIntent.putExtra("localDate", eventsLocalDate)
            myIntent.putExtra("time", eventsTime)
            myIntent.putExtra("text", eventsText)
            myIntent.putExtra("currentTime", LocalDate.of(date.year, date.monthValue, cellClicked.toInt()).toString())
            myIntent.putExtra("year", date.year)
            myIntent.putExtra("month", date.monthValue)
            myIntent.putExtra("day", date.dayOfMonth)
            startActivity(myIntent)
        }
    }
}