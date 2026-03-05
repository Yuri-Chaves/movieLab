package com.rocket.movielab.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rocket.movielab.ui.navigation.app_graph.appGraph

@Composable
fun MainNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.AppGraph.route,
        modifier = modifier.fillMaxSize()
    ) {
        appGraph(navController)
    }
}