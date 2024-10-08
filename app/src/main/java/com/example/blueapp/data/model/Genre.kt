package com.example.blueapp.data.model

import androidx.annotation.DrawableRes

data class Genre(
    val genreName: String,
    @DrawableRes
    val coverImage: Int,
    val books: List<Book>
)
