package com.rocket.movielab.data.remote.datasource

import com.rocket.movielab.data.model.MovieDetailsResult

interface MovieDetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsResult>
}
