package com.pants.cleannotes.util

import android.text.Editable
import android.text.TextWatcher

class NoteTextWatcher(private val callback: () -> Unit) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = callback()
    override fun afterTextChanged(s: Editable?) = Unit
}