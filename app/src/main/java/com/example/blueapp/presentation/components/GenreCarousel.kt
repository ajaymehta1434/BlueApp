package com.example.blueapp.presentation.components

import androidx.annotation.LayoutRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueapp.R


@Composable
fun GenreCarousel(@LayoutRes carouselItems: List<Int>, pagerState: PagerState) {
    Column(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dp_32))) {
        HorizontalPager(state = pagerState) {
            CarouselImage(carouselItems[it])
        }
        LazyRow(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dp_10))
                .align(Alignment.CenterHorizontally),
        ) {
            itemsIndexed(
                items = carouselItems
            ) { index, item ->
                TabIndicator(activeTab = index == pagerState.currentPage)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreviewGenreCarousel() {
    GenreCarousel(
        carouselItems = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        ),
        pagerState = rememberPagerState(initialPage = 0,
            pageCount = {
                2
            })
    )
}