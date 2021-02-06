package com.pants.data.cache.adapter

import com.pants.data.cache.room.NoteEntity
import com.pants.domain.Note
import java.util.*

class NoteAdapter {

    fun toDomain(note: NoteEntity, isFavourite: Boolean) = Note(
        id = note.id,
        title = note.title,
        text = note.text,
        modifiedDate = note.modifiedDate.time,
        isFavourite = isFavourite
    )

    fun toEntity(note: Note) = NoteEntity(
        id = note.id,
        title = note.title,
        text = note.text,
        modifiedDate = Date(note.modifiedDate)
    )
}