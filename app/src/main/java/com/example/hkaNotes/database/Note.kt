package com.example.hkaNotes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(

    val title: String,

    var content: String,

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
)
