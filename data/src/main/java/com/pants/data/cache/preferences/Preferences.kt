package com.pants.data.cache.preferences

interface Preferences {
    fun isFavourite(noteId: String): Boolean
    fun toggleFavourite(noteId: String): Boolean
}