package com.example.blueapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blueapp.R


@Composable
fun CarouselImage(@DrawableRes resourceId: Int) {
    Box(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.dp_10))
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_16)))
            .fillMaxSize(),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f),
            painter = painterResource(id = resourceId),
            contentDescription = "Carousel Item Image",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun PreviewCarouselImage() {
    CarouselImage(resourceId = R.drawable.ic_launcher_background)
}