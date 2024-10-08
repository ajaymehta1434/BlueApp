package com.example.blueapp.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.blueapp.R
import com.example.blueapp.data.model.Book
import com.example.blueapp.databinding.ActivityBooksBinding
import com.example.blueapp.presentation.adapter.BooksDataAdapter
import com.example.blueapp.presentation.adapter.GenreCarouselAdapter
import com.example.blueapp.presentation.dialog.BooksInsightsBottomSheet
import com.example.blueapp.presentation.utils.Extensions.toggleVisibility
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BooksActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityBooksBinding

    lateinit var genreCarouselAdapter: GenreCarouselAdapter
    lateinit var booksDataAdapter: BooksDataAdapter
    private val viewModel by viewModels<BooksViewModel>()
    private var booksListData = mutableListOf<Book>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        initDataBinding()
        attachObserver()
        getBookGenreData()
        setUpClickListener()
    }

    private fun initDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_books)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun attachObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.booksScreenState.collectLatest { state ->
                        when {
                            state.isPageLoading -> {
                                dataBinding.progressBar.toggleVisibility(true)
                            }

                            state.error != null -> {
                                dataBinding.progressBar.toggleVisibility(false)
                                state.error.let { error ->
                                    Toast.makeText(
                                        this@BooksActivity,
                                        error,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            else -> {
                                dataBinding.progressBar.toggleVisibility(false)
                                setBooksAdapterData()
                            }
                        }
                    }
                }

                launch {
                    viewModel.filterResult.collectLatest { filterData ->
                        booksListData.clear()
                        booksListData.addAll(filterData)
                        if (this@BooksActivity::booksDataAdapter.isInitialized) {
                            booksDataAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }


    private fun getBookGenreData() {
        viewModel.fetchBookGenreCatalog()
    }

    private fun setBooksAdapterData() {
        val genreImageList = viewModel.getGenreListData()
        genreCarouselAdapter = GenreCarouselAdapter(genreImageList)
        booksListData.clear()
        booksListData.addAll(viewModel.getBooksForSelectedGenre(0))
        booksDataAdapter = BooksDataAdapter(booksListData)
        dataBinding.apply {
            genreCarousel.viewPager.adapter = genreCarouselAdapter
            booksList.adapter = booksDataAdapter
            booksList.layoutManager = LinearLayoutManager(this@BooksActivity)
            TabLayoutMediator(
                genreCarousel.tabLayout,
                genreCarousel.viewPager
            ) { tab, position ->
            }.attach()
        }
    }

    private fun filterBooksItems(searchQuery: String?) {
        searchQuery?.let {
            viewModel.fetchFilteredResults(
                dataBinding.genreCarousel.viewPager.currentItem,
                searchQuery
            )
        }
    }

    private fun resetSearchView() {
        dataBinding.searchView.setQuery("", false)
        dataBinding.searchView.clearFocus()
    }

    private fun setUpClickListener() {
        dataBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterBooksItems(newText)
                return true
            }
        })

        dataBinding.genreCarousel.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                booksListData.clear()
                booksListData.addAll(viewModel.getBooksForSelectedGenre(position))
                if (this@BooksActivity::booksDataAdapter.isInitialized) {
                    booksDataAdapter.notifyDataSetChanged()
                }
                resetSearchView()
            }
        })

        dataBinding.fab.setOnClickListener {
            viewModel.getBooksInsightsData(dataBinding.genreCarousel.viewPager.currentItem)
            val modal = BooksInsightsBottomSheet()
            supportFragmentManager.let { modal.show(it, BooksInsightsBottomSheet.TAG) }
        }
    }
}

