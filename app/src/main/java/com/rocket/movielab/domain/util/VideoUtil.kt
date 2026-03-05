package com.rocket.movielab.domain.util

import com.rocket.movielab.data.model.Video

fun getBestVideo(videos: List<Video>): String? {
    fun List<Video>.sortedPriority(): List<Video> {
        return this.sortedWith(
            compareByDescending<Video> { it.size }
                .thenByDescending { it.publishedAt }
        )
    }

    fun Video.url(): String = "https://www.youtube.com/watch?v=$key"

    val youtubeVideos = videos.filter { it.site == "YouTube" }

    val officialTrailer = youtubeVideos
        .filter { it.type == "Trailer" && it.official }
        .sortedPriority()
        .firstOrNull()

    if (officialTrailer != null) return officialTrailer.url()

    val trailer = youtubeVideos
        .filter { it.type == "Trailer" }
        .sortedPriority()
        .firstOrNull()

    if (trailer != null) return trailer.url()

    val officialTeaser = youtubeVideos
        .filter { it.type == "Teaser" && it.official }
        .sortedPriority()
        .firstOrNull()

    if (officialTeaser != null) return officialTeaser.url()

    val teaser = youtubeVideos
        .filter { it.type == "Teaser" }
        .sortedPriority()
        .firstOrNull()

    if (teaser != null) return teaser.url()

    val officialFeaturette = youtubeVideos
        .filter { it.type == "Featurette" && it.official }
        .sortedPriority()
        .firstOrNull()

    if (officialFeaturette != null) return officialFeaturette.url()

    val featurette = youtubeVideos
        .filter { it.type == "Featurette" }
        .sortedPriority()
        .firstOrNull()

    if (featurette != null) return featurette.url()

    val anyYoutubeVideo = youtubeVideos
        .sortedPriority()
        .firstOrNull()

    if (anyYoutubeVideo != null) return anyYoutubeVideo.url()

    return null
}
