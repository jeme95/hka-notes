package com.example.hkaNotes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDAO {
    @Query("SELECT * from Note ORDER BY id DESC")
    fun getAll(): LiveData<List<Note>>

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM Note where id = :id")
    suspend fun deleteById(id: Long)


}
