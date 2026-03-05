package com.rocket.movielab.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResult(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    val id: Int,
    val title: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?, //https://image.tmdb.org/t/p/w500/
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val popularity: Double,
    @SerialName("release_date")
    val releaseDate: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)
