package com.example.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private var events: List<Event>, private val listener: CalendarInterface) : RecyclerView.Adapter<EventViewHolder>() {
    private val viewHolder: MutableList<EventViewHolder> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event, parent, false)
        viewHolder.add(EventViewHolder(view))
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.textViewHour.text = events[position].getHour()
        holder.textViewText.text = events[position].getText()
        holder.button.setOnClickListener {
            listener.dellEvent(events[position].getText(), events[position].getHour())
        }
    }
}