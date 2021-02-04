package com.pants.data.datasource

import com.pants.domain.Note

interface NoteApi {
    suspend fun getNote(id: String): Note?
    suspend fun getNotes(): List<Note>
    suspend fun saveNote(note: Note)
}