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
fun DeleteButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(
                RoundedCornerShape(6.dp)
            )
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = onClick)
        ,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.trash_outline),
            contentDescription = stringResource(R.string.delete_button),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview
@Composable
private fun DeleteButtonPreview() {
    MovieLabTheme{
        DeleteButton()
    }
}