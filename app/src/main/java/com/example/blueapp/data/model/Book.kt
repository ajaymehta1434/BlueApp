package com.example.blueapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes

data class Book(
    val name: String,
    @DrawableRes
    val image: Int,
    val author: String,
    val genre: String
)
