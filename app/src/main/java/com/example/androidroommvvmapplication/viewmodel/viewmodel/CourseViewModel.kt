package com.example.androidroommvvmapplication.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidroommvvmapplication.model.data.model.Course
import com.example.androidroommvvmapplication.model.repository.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(private val repository: CourseRepository):ViewModel() {
    var courseList: LiveData<List<Course>>

    init {
         courseList = repository.getAllCourses("id")
    }

    fun insertCourse(course: Course){
        viewModelScope.launch {
            repository.insertCourse(course)
        }

    }

     fun updateCourse(course: Course){
        viewModelScope.launch {
            repository.updateCourse(course)
        }
    }

     fun deleteCourse(course: Course){
        viewModelScope.launch {
            repository.deleteCourse(course)
        }
    }

    fun searchCourseByName(searchQuery:String){
        courseList = repository.searchCoursesByName(searchQuery)
    }

    fun getAlreadyStartedCourses(){
        courseList = repository.getAlreadyStartedCourses()
    }

    fun getAllCoursesSortedBasedOnName(){
        courseList = repository.getAllCourses("name")
    }

    fun getCoursesBasedOnType(type:String){
        courseList = repository.getCoursesBasedOnType(type)
    }

}