package com.example.blueapp.presentation.activity

import androidx.annotation.LayoutRes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blueapp.data.model.Book
import com.example.blueapp.domain.usecase.FetchBookGenreUseCase
import com.example.blueapp.domain.usecase.GetBooksInsightsUseCase
import com.example.blueapp.domain.utils.Resource
import com.example.blueapp.presentation.models.BooksInsights
import com.example.blueapp.presentation.ui.books.BooksScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val fetchBookGenreUseCase: FetchBookGenreUseCase,
    private val getBooksInsightsUseCase: GetBooksInsightsUseCase
) : ViewModel() {

    init {
        setInitialState()
        setInitialBottomSheetState()
    }

    private val initialState by lazy { setInitialState() }
    private val initialBottomSheetState by lazy { setInitialBottomSheetState() }

    private val _booksScreenState = MutableStateFlow(initialState)
    val booksScreenState: StateFlow<BooksScreenState> = _booksScreenState

    private val _bottomSheetState = MutableStateFlow(initialBottomSheetState)
    val bottomSheetState: StateFlow<BottomSheetState> = _bottomSheetState

    private val _filterResult = MutableStateFlow<List<Book>>(emptyList())
    val filterResult = _filterResult

    var searchQuery = mutableStateOf("")

    private fun setInitialState() = BooksScreenState(
        isPageLoading = false,
        genreList = null,
        error = null,
    )

    private fun setInitialBottomSheetState() = BottomSheetState(
        isBottomSheetLoading = false,
        bottomSheetData = null
    )


    fun fetchBooks() {
        _booksScreenState.value = _booksScreenState.value.copy(isPageLoading = true)
        viewModelScope.launch {
            val response = fetchBookGenreUseCase()
            when (response) {
                is Resource.Success -> {
                    _booksScreenState.value = _booksScreenState.value.copy(
                        isPageLoading = false,
                        genreList = response.data,
                        error = null
                    )
                }

                is Resource.Error -> {
                    _booksScreenState.value = _booksScreenState.value.copy(
                        isPageLoading = false,
                        genreList = null,
                        error = response.message
                    )
                }
            }
        }
    }

    fun getBooksForSelectedGenre(index: Int): List<Book> {
        return _booksScreenState.value.genreList?.get(index)?.books ?: emptyList()
    }

    fun getBooksInsightsData(currentIndex: Int) {
        _bottomSheetState.value = _bottomSheetState.value.copy(isBottomSheetLoading = true)
        viewModelScope.launch {
            try {
                val booksList = _booksScreenState.value.genreList ?: emptyList()
                val result = getBooksInsightsUseCase(
                    index = currentIndex,
                    genreList = booksList,
                )

                _bottomSheetState.value = _bottomSheetState.value.copy(
                    isBottomSheetLoading = false,
                    bottomSheetData = BooksInsights(booksList[currentIndex].books.count(), result)
                )

            } catch (exception: Exception) {
                _bottomSheetState.value = _bottomSheetState.value.copy(isBottomSheetLoading = true)
            }
        }
    }

    @LayoutRes
    fun getGenreListData(): List<Int> {
        return _booksScreenState.value.genreList?.map {
            it.coverImage
        } ?: emptyList()
    }

    fun fetchFilteredResults(
        genreIndex: Int,
        query: String
    ) {
        viewModelScope.launch {
            val searchPattern = ".*${query}.*"
            val searchRegex = Regex(searchPattern, RegexOption.IGNORE_CASE)

            val filteredResults = if (query.isNotEmpty()) {
                _booksScreenState.value.genreList?.get(genreIndex)?.books?.filter { book ->
                    searchRegex.containsMatchIn(book.name)
                }
            } else {
                getBooksForSelectedGenre(genreIndex)
            }
            _filterResult.emit(filteredResults ?: emptyList())
        }
    }

}

