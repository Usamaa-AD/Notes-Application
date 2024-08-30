package com.example.mvvmnotes.DatabaseFiles

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao){
    fun getAllNotes(): LiveData<List<NotesEntity>> {
        return notesDao.getAllNotes()
    }
    suspend fun insertNote(note: NotesEntity) {
        return notesDao.insertNote(note)
    }
    suspend fun updateNote(note: NotesEntity) {
        return notesDao.updateNote(note)
    }
    suspend fun deleteNote(note: NotesEntity) {
        return notesDao.deleteNote(note)
    }
    fun searchNotes(searchQuery: String): LiveData<List<NotesEntity>> {
        return notesDao.searchNoteByTitle(searchQuery)
    }
}