package com.example.androidroommvvmapplication.model.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidroommvvmapplication.model.data.model.Course

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)

    @Update
    suspend fun updateCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Query("Select * from Course order by :columnName asc")
    fun getAllCourses(columnName: String): LiveData<Course>

    @Query("Select * from Course where name like :searchQuery")
    fun searchCoursesByName(searchQuery: String): LiveData<Course>

    @Query("Select * from Course where startDate<CURDATE()")
    fun getAlreadyStartedCourses(): LiveData<Course>

    @Query("Select * from Course where type = :type")
    fun getCoursesBasedOnType(type:String): LiveData<Course>
}