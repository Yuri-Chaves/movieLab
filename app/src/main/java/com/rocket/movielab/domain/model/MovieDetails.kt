package com.rocket.movielab.domain.model

data class MovieDetails(
    val adult: Boolean,
    val backdropPath: String,
    val posterPath: String,
    val genres: List<String>,
    val id: Int,
    val overview: String,
    val releaseDate: String,
    val runtime: String,
    val title: String,
    val voteAverage: Double,
    val popularity: Double,
    val trailerUrl: String? = null,
    val isFavorite: Boolean = false
)
