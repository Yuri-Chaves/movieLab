package com.rocket.movielab.ui.screen.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.rocket.movielab.R
import com.rocket.movielab.domain.extensions.toBrazilian
import com.rocket.movielab.domain.extensions.toBrazilianDate
import com.rocket.movielab.domain.model.MovieDetails
import com.rocket.movielab.ui.component.FavoriteButton
import com.rocket.movielab.ui.theme.MovieLabTheme

@Composable
fun Info(
    title: String,
    value: String,
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.labelMedium.toSpanStyle()
            ) {
                append("$title: ")
            }
            withStyle(
                style = MaterialTheme.typography.bodyMedium.toSpanStyle()
            ) {
                append(value)
            }
        },
        color = MaterialTheme.colorScheme.inverseOnSurface,
    )
}

@SuppressLint("UseKtx")
@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    onEvent: (DetailsEvent) -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val outlineColor = MaterialTheme.colorScheme.surfaceTint

    val context = LocalContext.current

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (uiState.error != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.x_circle),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(48.dp)
            )
            Text(
                stringResource(R.string.sorry),
                style = MaterialTheme.typography.displayLarge, //titleLarge
                color = MaterialTheme.colorScheme.error
            )
            Text(
                stringResource(R.string.details_error_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center
            )
            Button(
                {},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    stringResource(R.string.try_again),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.surfaceContainerLow
                )
            }
        }
    }

    if (!uiState.isLoading && uiState.movie != null) {
        Column(
            modifier = modifier
                .statusBarsPadding()
                .padding(20.dp)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        24.dp
                    ),
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${uiState.movie.backdropPath}",
                            contentDescription = stringResource(
                                R.string.movie_poster,
                                uiState.movie.title
                            ),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.image_not_found),
                            error = painterResource(R.drawable.image_not_found),
                            modifier = Modifier
                                .matchParentSize()
                                .clip(
                                    RoundedCornerShape(10.dp)
                                )
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp, start = 8.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                                .clickable(onClick = onClickBack)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    8.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.arrow_left),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.inverseOnSurface,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    stringResource(R.string.back_button),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.inverseOnSurface
                                )
                            }
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        Text(
                            uiState.movie.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    2.dp
                                ),
                            ) {
                                Info(
                                    stringResource(R.string.runtime_title),
                                    uiState.movie.runtime
                                )
                                Info(
                                    stringResource(R.string.release_date_title),
                                    uiState.movie.releaseDate.toBrazilianDate()
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    6.dp
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MaterialTheme.colorScheme.surface)
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.star),
                                    contentDescription = stringResource(R.string.rate),
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(
                                        4.dp
                                    ),
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Text(
                                        uiState.movie.voteAverage.toBrazilian(1),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.inverseOnSurface
                                    )
                                    Text(
                                        stringResource(R.string.rating_limit),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.inverseOnSurface
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                val strokeWidth = 1.dp.toPx()
                                val y2 = strokeWidth / 2
                                drawLine(
                                    color = outlineColor,
                                    start = Offset(0f, y2),
                                    end = Offset(size.width, y2),
                                    strokeWidth = strokeWidth
                                )
                            }
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        items(uiState.movie.genres.size) { index ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MaterialTheme.colorScheme.surface)
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                            ) {
                                Text(
                                    uiState.movie.genres[index],
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.inverseOnSurface
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .drawBehind {
                                val strokeWidth = 1.dp.toPx()
                                val y1 = strokeWidth / 2
                                val y2 = size.height - strokeWidth / 2

                                drawLine(
                                    color = outlineColor,
                                    start = Offset(0f, y1),
                                    end = Offset(size.width, y1),
                                    strokeWidth = strokeWidth
                                )
                                drawLine(
                                    color = outlineColor,
                                    start = Offset(0f, y2),
                                    end = Offset(size.width, y2),
                                    strokeWidth = strokeWidth
                                )
                            }
                            .padding(vertical = 16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            uiState.movie.overview,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                ),
            ) {
                FavoriteButton(
                    uiState.isFavorite,
                    {
                        onEvent(DetailsEvent.OnToggleFavorite(uiState.movie))
                    }
                )
                Spacer(Modifier.weight(1f))
                if (uiState.movie.trailerUrl != null) {
                    Button(
                        {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                uiState.movie.trailerUrl.toUri()
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                8.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.youtube_logo_outline),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.surfaceContainerLow,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                stringResource(R.string.trailer),
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.surfaceContainerLow
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
private fun DetailsScreenPreview() {
    MovieLabTheme {
        DetailsScreen(
            DetailsUiState(
                isLoading = false,
                error = null,
                movie = MovieDetails(
                    adult = false,
                    backdropPath = "/c6OLXfKAk5BKeR6broC8pYiCquX.jpg",
                    posterPath = "/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg",
                    genres = listOf("Drama", "Thriller"),
                    id = 550,
                    overview = "Um homem deprimido que sofre de insônia conhece um estranho vendedor de sabonetes chamado Tyler Durden. Eles formam um clube clandestino com regras rígidas onde lutam com outros homens cansados de suas vidas mundanas. Mas sua parceria perfeita é comprometida quando Marla chama a atenção de Tyler.",
                    releaseDate = "1999-10-15",
                    runtime = "2h 19min",
                    title = "Clube da Luta",
                    voteAverage = 8.4,
                    popularity = 100.0
                )
            ),
            {},
            {}
        )
    }
}