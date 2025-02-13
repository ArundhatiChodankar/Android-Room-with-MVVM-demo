package com.example.androidroommvvmapplication.model.repository

import com.example.androidroommvvmapplication.model.data.model.Course
import com.example.androidroommvvmapplication.model.data.room.AppDatabase

class CourseRepository(private val db: AppDatabase) {

    suspend fun insertCourse(course: Course) = db.getCourseDao().insertCourse(course)

    suspend fun updateCourse(course: Course) = db.getCourseDao().updateCourse(course)

    suspend fun deleteCourse(course: Course) = db.getCourseDao().deleteCourse(course)

    fun getAllCourses(columnName: String) = db.getCourseDao().getAllCourses(columnName)

    fun searchCoursesByName(searchQuery: String) =
        db.getCourseDao().searchCoursesByName(searchQuery)

    fun getAlreadyStartedCourses() = db.getCourseDao().getAlreadyStartedCourses()

    fun getCoursesBasedOnType(type: String) = db.getCourseDao().getCoursesBasedOnType(type)
}