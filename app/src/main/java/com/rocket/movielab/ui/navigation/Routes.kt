package com.rocket.movielab.ui.navigation

sealed class Routes(val route: String) {
    data object AppGraph : Routes("app_graph")
    data object BottomGraph : Routes("bottom_graph")

    data object Trending : Routes("trending")
    data object Favorite : Routes("favorite")
    data object Find : Routes("find")

    data object Details : Routes("details/{movieId}") {
        fun createRoute(movieId: Int) = "details/$movieId"

        const val MOVIE_ID = "movieId"
    }
}