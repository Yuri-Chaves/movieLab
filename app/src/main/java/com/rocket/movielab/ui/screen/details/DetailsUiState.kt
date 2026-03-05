package com.rocket.movielab.ui.screen.details

import com.rocket.movielab.domain.model.MovieDetails

data class DetailsUiState(
    val movie: MovieDetails? = null,
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val error: String? = null
)
