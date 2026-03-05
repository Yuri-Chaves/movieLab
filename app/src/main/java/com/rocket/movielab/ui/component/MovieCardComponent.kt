package com.rocket.movielab.ui.component

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rocket.movielab.R
import com.rocket.movielab.domain.extensions.getYear
import com.rocket.movielab.domain.extensions.toBrazilian
import com.rocket.movielab.domain.model.MovieCard
import com.rocket.movielab.ui.theme.Gray600
import com.rocket.movielab.ui.theme.Gray700
import com.rocket.movielab.ui.theme.MovieLabTheme


@Composable
fun MovieCardComponent(
    movieCard: MovieCard,
    onClick: () -> Unit,
    onRemoveFavorite: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .width(173.dp)
            .height(222.dp)
            .clip(
                RoundedCornerShape(8.dp)
            )
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movieCard.posterPath}",
            contentDescription = stringResource(R.string.movie_poster, movieCard.title),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.image_not_found),
            error = painterResource(R.drawable.image_not_found),
            modifier = Modifier.matchParentSize()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.75f to MaterialTheme.colorScheme.scrim.copy(alpha = .8f),
                            1.0f to MaterialTheme.colorScheme.scrim.copy(alpha = .95f)
                        )
                    )
                )
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
                .clickable(onClick = onClick),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (onRemoveFavorite != null) {
                DeleteButton(
                    { onRemoveFavorite() },
                    Modifier.align(Alignment.End)
                )
            }
            if (movieCard.adult) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(
                            RoundedCornerShape(6.dp)
                        )
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable(onClick = onClick)
                        .align(Alignment.Start),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "+18",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(
                    4.dp,
                    Alignment.CenterVertically
                )
            ) {
                Text(
                    movieCard.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = Gray700
                )
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(
                        6.dp,
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp,
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = stringResource(R.string.rate),
                            tint = Gray700,
                            modifier = Modifier.size(10.dp)
                        )
                        Text(
                            movieCard.voteAverage.toBrazilian(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Gray600
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.divider),
                        contentDescription = null,
                        tint = Gray600
                    )
                    Text(
                        movieCard.releaseDate.getYear(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Gray600
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MovieCardComponentPreview() {
    MovieLabTheme {
        MovieCardComponent(
            MovieCard(
                1,
                false,
                "Zootopia 2",
                "/4acLcG3TFrgZ82qZtrP9ZqJ8QZS.jpg",
                "2025-01-01",
                7.604,
                164.976
            ),
            {}
        )
    }
}