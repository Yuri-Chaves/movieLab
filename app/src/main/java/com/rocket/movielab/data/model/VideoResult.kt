package com.rocket.movielab.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResult(
    val id: Int,
    val results: List<Video>
)

@Serializable
data class Video(
    val key: String,
    val site: String,
    val type: String,
    val official: Boolean,
    val size: Int,
    @SerialName("published_at")
    val publishedAt: String
)
