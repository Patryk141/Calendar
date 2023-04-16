package com.example.calendar

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.recyclerview.widget.RecyclerView

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textViewHour : TextView = view.findViewById<TextView>(R.id.textViewHours)
    var textViewText : TextView = view.findViewById<TextView>(R.id.textViewText)
    var button : Button = view.findViewById<Button>(R.id.button7)
}