package com.rocket.movielab.ui.navigation.app_graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.rocket.movielab.ui.navigation.Routes
import com.rocket.movielab.ui.navigation.app_graph.bottom_graph.BottomNav
import com.rocket.movielab.ui.screen.details.DetailsScreen
import com.rocket.movielab.ui.screen.details.DetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.appGraph(navController: NavController) {
    navigation(
        startDestination = Routes.BottomGraph.route,
        route = Routes.AppGraph.route
    ) {
        composable(route = Routes.BottomGraph.route) {
            BottomNav(navController)
        }

        composable(
            route = Routes.Details.route,
            arguments = listOf(
                navArgument(Routes.Details.MOVIE_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments
                ?.getInt(Routes.Details.MOVIE_ID)
                ?: error("Movie id not found")

            val viewModel: DetailsViewModel = koinViewModel(
                parameters = { parametersOf(movieId) }
            )
            val uiState by viewModel.uiState.collectAsState()

            DetailsScreen(
                uiState,
                viewModel::onEvent,
                { navController.popBackStack() },
            )
        }
    }
}