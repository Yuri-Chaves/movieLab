package com.rocket.movielab.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rocket.movielab.R
import com.rocket.movielab.ui.navigation.Routes

enum class PagesEnum(
    val route: String,
    @param:DrawableRes val icon: Int,
    @param:StringRes val label: Int,
    @param:StringRes val description: Int
) {
    TRENDING(
        Routes.Trending.route,
        R.drawable.filmslate_outline,
        R.string.trending_label,
        R.string.trending_description
    ),
    FIND(
        Routes.Find.route,
        R.drawable.magnify,
        R.string.find_label,
        R.string.find_description
    ),
    FAVORITES(
        Routes.Favorite.route,
        R.drawable.bookmark_multiple_outline,
        R.string.favorites_label,
        R.string.favorites_description
    ),

}