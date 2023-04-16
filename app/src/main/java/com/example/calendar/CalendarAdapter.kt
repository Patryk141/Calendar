package com.example.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(private var cells: List<String>,private var today: String, private val listener: CalendarInterface) : RecyclerView.Adapter<CalendarViewHolder>() {
    private val viewHolder: MutableList<CalendarViewHolder> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_cell, parent, false)
        viewHolder.add(CalendarViewHolder(view))
        return CalendarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cells.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.textView.text = cells[position]
        if (cells[position] == today) {
            holder.textView.setBackgroundResource(R.drawable.circle)
            holder.textView.setTextColor(Color.WHITE)
        }
        holder.textView.setOnClickListener {
            if (cells[position] != "") {
                listener.cellClick(cells[position])
                holder.textView.setBackgroundResource(R.drawable.ring)
                for (i in 0..41) {
                    if (i != position) {
                        viewHolder[i].textView.setBackgroundColor(Color.WHITE)
                    }
                    if (cells[i] == today) {
                        viewHolder[i].textView.setBackgroundResource(R.drawable.circle)
                        viewHolder[i].textView.setTextColor(Color.WHITE)
                    }
                }
            }
        }
        if (position%7 == 6 && cells[position] != today) {
            holder.textView.setTextColor(Color.RED)
        }
    }
}