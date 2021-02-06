package com.pants.domain

data class Note(
    val id: String,
    val title: String,
    val text: String,
    val modifiedDate: Long,
    val isFavourite: Boolean
)