package com.example.androidroommvvmapplication.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidroommvvmapplication.model.repository.CourseRepository
import com.example.androidroommvvmapplication.viewmodel.viewmodel.CourseViewModel

class CourseViewModelProviderFactory(private val courseRepository:CourseRepository) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            return CourseViewModel(courseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}