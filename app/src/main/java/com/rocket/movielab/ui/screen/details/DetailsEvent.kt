package com.rocket.movielab.ui.screen.details

import com.rocket.movielab.domain.model.MovieDetails

interface DetailsEvent {
    data class OnToggleFavorite(val movie: MovieDetails) : DetailsEvent
}