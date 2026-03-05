package com.rocket.movielab.ui.navigation.app_graph.bottom_graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rocket.movielab.ui.component.PagesEnum
import com.rocket.movielab.ui.navigation.Routes
import com.rocket.movielab.ui.theme.MovieLabTheme

@Composable
fun BottomNavigator(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var currentDestination by rememberSaveable { mutableStateOf(PagesEnum.TRENDING.route) }
    val outlineColor = MaterialTheme.colorScheme.surfaceTint

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.height(84.dp)
            .drawBehind{
                val strokeWidth = 1.dp.toPx()
                val y = strokeWidth / 2
                drawLine(
                    color = outlineColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        PagesEnum.entries.forEach {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(it.icon),
                        contentDescription = stringResource(it.label),
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = currentDestination == it.route,
                onClick = {
                    currentDestination = it.route
                    navController.navigate(it.route)
                },
                label = {
                    Text(
                        stringResource(it.label),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigatorPreview() {
    MovieLabTheme{
        BottomNavigator(
            navController = NavController(LocalContext.current)
        )
    }
}