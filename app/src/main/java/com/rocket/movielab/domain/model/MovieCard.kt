package com.rocket.movielab.domain.model

data class MovieCard(
    val id: Int,
    val adult: Boolean,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val popularity: Double
)
