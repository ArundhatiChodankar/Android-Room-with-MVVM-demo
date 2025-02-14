package com.example.androidroommvvmapplication.view.interfaces

import com.example.androidroommvvmapplication.model.data.model.Course

interface ItemClickListener {
    fun onItemDeleteClick(course: Course)
    fun onItemEditClick(course: Course)
}