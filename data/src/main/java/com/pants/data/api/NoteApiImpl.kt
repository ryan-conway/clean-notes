package com.pants.data.api

import com.pants.data.datasource.NoteApi
import com.pants.domain.Note

/**
 * stub
 */
class NoteApiImpl : NoteApi {
    override suspend fun getNote(id: String): Note? = null

    override suspend fun getNotes(): List<Note> = emptyList()

    override suspend fun saveNote(note: Note) = Unit
}