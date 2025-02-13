package com.example.androidroommvvmapplication.model.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidroommvvmapplication.model.data.model.Course

@Database(entities = [Course::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCourseDao(): CourseDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(this) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "course_db.db"
        ).build()
    }
}