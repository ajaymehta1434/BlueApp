package com.example.blueapp.presentation.activity

import com.example.blueapp.presentation.models.BooksInsights

data class BottomSheetState(
    val bottomSheetData: BooksInsights? = null,
    val isBottomSheetLoading: Boolean,
)


