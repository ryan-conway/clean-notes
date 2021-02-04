package com.pants.domain.repository

import com.pants.domain.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getNote(id: String): Note?
    fun getNotes(): Flow<List<Note>>
    suspend fun saveNote(note: Note)
    suspend fun toggleFavourite(note: Note): Boolean
}