package com.example.mvvmnotes.DatabaseFiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    fun getAllNotes(): LiveData<List<NotesEntity>> {
        return notesRepository.getAllNotes()
    }

    fun insertNote(note: NotesEntity) {
        viewModelScope.launch {
            notesRepository.insertNote(note)
        }
    }

    fun updateNote(note: NotesEntity) {
        viewModelScope.launch {
            notesRepository.updateNote(note)
        }
    }

    fun deleteNode(note: NotesEntity) {
        viewModelScope.launch {
            notesRepository.deleteNote(note)
        }
    }
    fun searchNotes(searchQuery: String): LiveData<List<NotesEntity>> {
        return notesRepository.searchNotes(searchQuery)
    }


}