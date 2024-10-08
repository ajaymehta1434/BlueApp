package com.example.blueapp.presentation.activity

import androidx.annotation.StringRes
import com.example.blueapp.data.model.Genre
import com.example.blueapp.presentation.models.BooksInsights

data class BooksScreenState(
    val isPageLoading:Boolean,
    val isBottomSheetLoading:Boolean,
    val genreList:List<Genre>?,
    @StringRes
    val error: Int? = null
)