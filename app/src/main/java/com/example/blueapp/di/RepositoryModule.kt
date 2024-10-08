package com.example.blueapp.di

import com.example.blueapp.data.repository.BooksRepositoryImp
import com.example.blueapp.domain.repository.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideCarouselRepository(booksRepository: BooksRepositoryImp): BooksRepository
}
