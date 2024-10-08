package com.example.blueapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueapp.R
import com.example.blueapp.presentation.activity.BottomSheetState
import com.example.blueapp.presentation.models.BooksInsights
import com.example.blueapp.presentation.theme.LocalCustomColorPalette


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetInsightsView(
    bottomSheetDetailsState: BottomSheetState,
    onDismiss: () -> Unit,
    modalState: SheetState,
) {

    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = { onDismiss() },
        sheetState = modalState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = LocalCustomColorPalette.current.screenBackground
    ) {
        when {
            bottomSheetDetailsState.isBottomSheetLoading -> {
                BlueAppLoader()
            }

            bottomSheetDetailsState.bottomSheetData != null -> {
                val bottomSheetData = bottomSheetDetailsState.bottomSheetData
                Column(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.dp_32),
                            start = dimensionResource(id = R.dimen.dp_16)
                        )
                ) {
                    Row {
                        Text(
                            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.dp_3)),
                            text = stringResource(id = R.string.number_of_items),
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            text = bottomSheetDetailsState.bottomSheetData.itemCount.toString(),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }

                    Text(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dp_16)),
                        text = stringResource(id = R.string.top_characters),
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dp_8))) {
                        repeat(bottomSheetData.characters.size) { index ->
                            Row(Modifier.padding(top = dimensionResource(id = R.dimen.dp_8))) {
                                Text(
                                    text = stringResource(id = R.string.bullet_symbol).plus(" ").plus(
                                        bottomSheetData.characters[index].first.toString().plus(" ")
                                            .plus(stringResource(id = R.string.colon_symbol)).plus(" ")
                                    ),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = bottomSheetData.characters[index].second.toString(),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewBottomSheet() {
    BottomSheetInsightsView(
        bottomSheetDetailsState = BottomSheetState(
            bottomSheetData = BooksInsights(
                5,
                listOf(Pair('r', 5), Pair('r', 5))
            ),
            isBottomSheetLoading = false
        ),
        onDismiss = { },
        modalState = rememberModalBottomSheetState()
    )
}