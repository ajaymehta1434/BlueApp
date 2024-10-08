package com.example.blueapp.presentation.ui.books

import androidx.annotation.StringRes
import com.example.blueapp.data.model.Genre

data class BooksScreenState(
    val isPageLoading: Boolean,
    val genreList: List<Genre>?,
    @StringRes
    val error: Int? = null
)