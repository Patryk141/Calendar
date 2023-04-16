package com.example.calendar

import java.time.LocalDate

class Event(date: String, hour: String, text: String) {

    private var date: String;
    private var hour: String
    private var text: String

    fun getDate(): String {
        return date
    }
    fun getHour(): String {
        return hour
    }
    fun getText(): String {
        return text
    }
    init {
        this.date = date
        this.hour = hour
        this.text = text
    }
}