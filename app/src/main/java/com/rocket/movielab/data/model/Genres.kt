package com.rocket.movielab.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Genres(
    val id: Int,
    val name: String
)
