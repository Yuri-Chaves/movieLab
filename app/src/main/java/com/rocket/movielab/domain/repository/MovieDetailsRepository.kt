package com.rocket.movielab.domain.repository

import com.rocket.movielab.data.model.Video
import com.rocket.movielab.domain.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getMovieTrailers(movieId: Int): List<Video>
}