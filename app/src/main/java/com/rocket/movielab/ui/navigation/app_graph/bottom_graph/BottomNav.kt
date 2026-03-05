package com.rocket.movielab.ui.navigation.app_graph.bottom_graph

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.rocket.movielab.domain.model.MovieCard
import com.rocket.movielab.ui.navigation.Routes
import com.rocket.movielab.ui.screen.favorite.FavoriteViewModel
import com.rocket.movielab.ui.screen.favorite.FavoritesScreen
import com.rocket.movielab.ui.screen.find.FindScreen
import com.rocket.movielab.ui.screen.find.FindViewModel
import com.rocket.movielab.ui.screen.trending.TrendingScreen
import com.rocket.movielab.ui.screen.trending.TrendingViewModel
import kotlinx.coroutines.flow.flowOf
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNav(
    rootNavController: NavController
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigator(navController) }
    ) { _ ->
        NavHost(
            navController = navController,
            startDestination = Routes.Trending.route
        ) {
            composable(route = Routes.Trending.route) {
                val viewModel: TrendingViewModel = koinViewModel()

                TrendingScreen(
                    viewModel.movies.collectAsLazyPagingItems(),
                    onMovieClick = { id ->
                        rootNavController.navigate(
                            Routes.Details.createRoute(id)
                        )
                    }
                )
            }
            composable(route = Routes.Find.route) {
                val viewModel = koinViewModel<FindViewModel>()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val hasSearched by viewModel.hasSearched.collectAsState()

                FindScreen(
                    viewModel.searchResults.collectAsLazyPagingItems(),
                    hasSearched,
                    uiState,
                    viewModel::onEvent,
                    onMovieClick = { id ->
                        rootNavController.navigate(
                            Routes.Details.createRoute(id)
                        )
                    }
                )
            }
            composable(route = Routes.Favorite.route) {
                val viewModel = koinViewModel<FavoriteViewModel>()

                FavoritesScreen(
                    viewModel.favoriteMovies.collectAsLazyPagingItems(),
                    viewModel::onEvent,
                    { id ->
                        rootNavController.navigate(
                            Routes.Details.createRoute(id)
                        )
                    }
                )
            }
        }
    }
}