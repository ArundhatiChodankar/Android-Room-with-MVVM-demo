package com.example.androidroommvvmapplication.view.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CommonUtils {
    companion object {
        fun getDateInDisplayFormat(calender: Date): String {
            val dateFormat = "dd MMM yyyy"
            val sdf = SimpleDateFormat(dateFormat, Locale.US)
            return sdf.format(calender.time)
        }
    }
}