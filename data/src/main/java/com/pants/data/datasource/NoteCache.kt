package com.pants.data.datasource

import com.pants.domain.Note
import kotlinx.coroutines.flow.Flow

interface NoteCache {
    suspend fun getNote(id: String): Note?
    fun getNotes(): Flow<List<Note>>
    suspend fun saveNote(note: Note)
    suspend fun saveNotes(notes: List<Note>)
    suspend fun deleteNote(note: Note)
    suspend fun toggleFavourite(note: Note): Boolean
}