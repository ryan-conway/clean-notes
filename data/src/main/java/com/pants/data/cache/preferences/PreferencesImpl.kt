package com.pants.data.cache.preferences

import android.content.Context

class PreferencesImpl(context: Context) : Preferences {

    private val sharedPreferences =
        context.getSharedPreferences(FAVOURITE_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

    override fun isFavourite(noteId: String): Boolean {
        return sharedPreferences.getBoolean(noteId, false)
    }

    override fun toggleFavourite(noteId: String): Boolean {
        val isFavourite = isFavourite(noteId)
        sharedPreferences.edit().putBoolean(noteId, !isFavourite).apply()
        return !isFavourite
    }

    companion object {
        const val FAVOURITE_PREFERENCES_FILENAME = "favourite_notes"
    }
}