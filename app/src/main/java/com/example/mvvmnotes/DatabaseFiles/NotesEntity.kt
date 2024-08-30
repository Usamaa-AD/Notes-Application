package com.example.mvvmnotes.DatabaseFiles

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content:String
)