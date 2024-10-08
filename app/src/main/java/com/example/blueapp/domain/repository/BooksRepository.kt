package com.example.blueapp.domain.repository

import com.example.blueapp.data.model.Genre


interface BooksRepository {
    suspend fun loadBookGenreCatalog(): List<Genre>
}