package com.example.mvvmnotes.DatabaseFiles

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Insert
  suspend  fun insertNote(note: NotesEntity)
    @Update
    suspend fun updateNote(note: NotesEntity)
    @Delete
    suspend fun deleteNote(note: NotesEntity)
    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<NotesEntity>>
  @Query("SELECT * FROM notes WHERE title LIKE '%' || :searchQuery || '%'")
  fun searchNoteByTitle(searchQuery: String):LiveData<List<NotesEntity>>
}