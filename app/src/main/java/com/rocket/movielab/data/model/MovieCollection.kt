package com.rocket.movielab.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCollection(
    val id: Int,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String
)
