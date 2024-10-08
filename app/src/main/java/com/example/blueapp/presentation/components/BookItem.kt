package com.example.blueapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueapp.R
import com.example.blueapp.data.model.Book
import com.example.blueapp.presentation.theme.LocalCustomColorPalette

@Composable
fun BookItemView(bookItem: Book) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_16)))
            .background(
                LocalCustomColorPalette.current.tertiaryColor
            )
            .padding(vertical = dimensionResource(id = R.dimen.dp_5))
    ) {
        Row(
            Modifier
                .padding(dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.dp_60))
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_16))),
                painter = painterResource(id = bookItem.image),
                contentDescription = "Book Image",
                contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.dp_16))
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = bookItem.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dp_4)),
                    text = bookItem.author,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBookItemView() {
    BookItemView(
        bookItem = Book(
            name = "The Great Gatsby",
            image = R.drawable.ic_launcher_background,
            author = "F. Scott Fitzgerald",
            genre = "Fiction"
        )
    )
}