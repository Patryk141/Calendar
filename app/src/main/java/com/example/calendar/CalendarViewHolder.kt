package com.example.calendar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textView : TextView = view.findViewById<TextView>(R.id.textViewCell)
}