package com.example.blueapp.data.repository

import com.example.blueapp.data.datasource.BooksDataSource
import com.example.blueapp.data.model.Genre
import com.example.blueapp.domain.repository.BooksRepository
import javax.inject.Inject

class BooksRepositoryImp @Inject constructor() : BooksRepository {

    override suspend fun loadBookGenreCatalog(): List<Genre> {
        return BooksDataSource.genreList
    }
}