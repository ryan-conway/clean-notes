package com.pants.data.cache.adapter

import com.pants.data.cache.room.NoteEntity
import com.pants.domain.Note

class NoteAdapter {

    fun toDomain(note: NoteEntity, isFavourite: Boolean) = Note(
        id = note.id,
        title = note.title,
        text = note.text,
        isFavourite = isFavourite
    )

    fun toEntity(note: Note) = NoteEntity(
        id = note.id,
        title = note.title,
        text = note.text
    )
}