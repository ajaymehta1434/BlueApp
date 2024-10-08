package com.example.blueapp.domain.usecase

import com.example.blueapp.R
import com.example.blueapp.data.model.Genre
import com.example.blueapp.domain.repository.BooksRepository
import com.example.blueapp.domain.utils.Resource
import javax.inject.Inject

class FetchBookGenreUseCase @Inject constructor(private val booksRepository: BooksRepository) {

    suspend operator fun invoke(): Resource<List<Genre>> {
        return try {
            val booksCatalogue = booksRepository.loadBookGenreCatalog()
            Resource.Success(booksCatalogue)
        } catch (exception: Exception) {
            Resource.Error(message = R.string.no_data_found)
        }
    }
}