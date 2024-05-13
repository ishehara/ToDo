package com.example.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

//table
@Entity(tableName = "To_Do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var task:String,

    )