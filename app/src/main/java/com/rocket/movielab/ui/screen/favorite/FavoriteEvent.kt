package com.rocket.movielab.ui.screen.favorite

interface FavoriteEvent {
    data class RemoveFavorite(val movieId: Int): FavoriteEvent
}