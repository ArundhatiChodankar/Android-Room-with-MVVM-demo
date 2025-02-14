package com.example.androidroommvvmapplication.model.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "Course")
data class Course(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var description: String,
    var duration: String,
    var startDate: Date,
    var type: String,
):Serializable
