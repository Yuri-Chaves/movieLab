package com.rocket.movielab.domain.usecase

import com.rocket.movielab.domain.model.MovieDetails
import com.rocket.movielab.domain.repository.MovieDetailsRepository
import com.rocket.movielab.domain.util.getBestVideo

class MovieDetailsUseCase(
    val repository: MovieDetailsRepository
) {
    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val details = repository.getMovieDetails(movieId)
        val trailer = getBestVideo(repository.getMovieTrailers(movieId))

        return details.copy( trailerUrl = trailer )
    }
}