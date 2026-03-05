package com.rocket.movielab.ui.screen.find

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rocket.movielab.R
import com.rocket.movielab.domain.model.MovieCard
import com.rocket.movielab.ui.component.EmptyList
import com.rocket.movielab.ui.component.MovieCardComponent
import com.rocket.movielab.ui.component.PagesEnum
import com.rocket.movielab.ui.component.ScreenHeader
import com.rocket.movielab.ui.theme.MovieLabTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun FindScreen(
    results: LazyPagingItems<MovieCard>,
    hasSearched: Boolean,
    uiState: FindUiState,
    onEvent: (FindEvent) -> Unit,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val value = uiState.query

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenHeader(PagesEnum.FIND)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 4.dp
                )
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = { onEvent(FindEvent.OnSearchChange(it)) },
                singleLine = true,
                placeholder = {
                    Text(
                        stringResource(R.string.find_placeholder),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.magnify),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = if (value.isNotEmpty())
                            MaterialTheme.colorScheme.secondary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                },
                trailingIcon = {
                    if (value.isNotEmpty()) {
                        IconButton({ onEvent(FindEvent.OnCleanSearch) }) {
                            Icon(
                                painter = painterResource(R.drawable.x_circle),
                                contentDescription = "Limpar",
                                modifier = Modifier.size(20.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    unfocusedTextColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceTint,
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                        keyboardController?.hide()
                    }
                )
            )
        }
        when (val state = results.loadState.refresh) {
            is LoadState.Loading -> {
                if (hasSearched) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    EmptyList(R.string.no_search_label)
                }
            }

            is LoadState.Error -> {
                Log.e("FindScreen", "Erro no refresh: ${state.error}")
                EmptyList(
                    R.string.error_description,
                    R.string.error_title,
                    true
                )
            }

            is LoadState.NotLoading -> {
                if (!hasSearched) {
                    EmptyList(R.string.no_search_label)
                }
                if (results.itemCount == 0 && hasSearched) {
                    EmptyList(R.string.movie_not_found)
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(results.itemCount) { position ->
                            results[position]?.let { movieCard ->
                                MovieCardComponent(
                                    movieCard,
                                    onClick = { onMovieClick(movieCard.id) },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FindScreenPreview() {
    MovieLabTheme {
        val pagingData = PagingData.from(emptyList<MovieCard>())
        val lazyPagingItems = flowOf(pagingData).collectAsLazyPagingItems()
        FindScreen(
            lazyPagingItems,
            false,
            FindUiState(),
            {},
            {}
        )
    }
}