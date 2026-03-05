package com.rocket.movielab.ui.screen.trending

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun TrendingScreen(
    movies: LazyPagingItems<MovieCard>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ScreenHeader(PagesEnum.TRENDING)
        when (val state = movies.loadState.refresh) {
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadState.Error -> {
                Log.e("TrendingScreen", "Erro no refresh: ${state.error}", )
                EmptyList(
                    R.string.error_description,
                    R.string.error_title,
                    true
                )
            }
            is LoadState.NotLoading if movies.itemCount == 0 -> {
                EmptyList(
                    R.string.movie_not_found
                )
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(movies.itemCount) { position ->
                        movies[position]?.let { movieCard ->
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

@Preview
@Composable
private fun TrendingScreenPreview() {
    val fakeMovies = List(5) {
        MovieCard(
            id = it,
            adult = false,
            title = "Movie Title",
            posterPath = "",
            releaseDate = "2023",
            voteAverage = 5.0,
            popularity = 5.0
        )
    }
    val pagingData = PagingData.from(fakeMovies)
    val lazyPagingItems = flowOf(pagingData).collectAsLazyPagingItems()

    MovieLabTheme {
        TrendingScreen(lazyPagingItems, {})
    }
}