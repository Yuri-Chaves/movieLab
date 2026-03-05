package com.rocket.movielab.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocket.movielab.R
import com.rocket.movielab.ui.theme.MovieLabTheme

@Composable
fun EmptyList(
    @StringRes description: Int,
    @StringRes title: Int? = null,
    inError: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = 48.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            12.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.listbullets),
            contentDescription = null,
            tint = if (inError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(44.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(
                4.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (title != null) {
                Text(
                    stringResource(title),
                    style = MaterialTheme.typography.labelLarge,
                    color = if (inError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                stringResource(description),
                style = MaterialTheme.typography.bodyMedium,
                color = if (inError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun EmptyListPreview() {
    MovieLabTheme {
        EmptyList(
            R.string.favorite_empty_list_description,
            R.string.favorite_empty_list_title
        )
    }
}