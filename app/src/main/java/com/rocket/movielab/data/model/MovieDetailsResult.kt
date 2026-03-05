package com.rocket.movielab.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResult(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val belongsToCollection: MovieCollection? = null,
    val budget: Int,
    val genres: List<Genres>,
    val homepage: String,
    val id: Int,
    val imdbId: String? = null,
    @SerialName("original_country")
    val originalCountry: List<String>? = null,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)
