package com.rocket.movielab.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocket.movielab.R
import com.rocket.movielab.ui.theme.MovieLabTheme

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = if (isFavorite) {
        R.drawable.bookmark
    } else {
        R.drawable.bookmark_outline
    }
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(
                RoundedCornerShape(6.dp)
            )
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.favorite_button),
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(
    backgroundColor = 0xFF0F0F1A,
    showBackground = true
)
@Composable
private fun FavoriteButtonPreviewDark() {
    MovieLabTheme {
        FavoriteButton(false, {})
    }
}

@Preview(
    backgroundColor = 0xFFE4E5EC,
    showBackground = true
)
@Composable
private fun FavoriteButtonPreviewLight() {
    MovieLabTheme {
        FavoriteButton(true, {})
    }
}