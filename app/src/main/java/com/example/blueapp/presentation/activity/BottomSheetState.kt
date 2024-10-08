package com.example.blueapp.presentation.activity

import androidx.annotation.StringRes
import com.example.blueapp.data.model.Genre
import com.example.blueapp.presentation.models.BooksInsights

data class BottomSheetState(
    val bottomSheetData:BooksInsights? = null,
    val isBottomSheetLoading:Boolean,
)