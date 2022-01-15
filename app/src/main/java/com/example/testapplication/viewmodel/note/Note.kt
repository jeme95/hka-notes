package com.example.testapplication.viewmodel.note

data class Note(
    val title: String,
    val content: String,
){
    val id = System.currentTimeMillis()
}
