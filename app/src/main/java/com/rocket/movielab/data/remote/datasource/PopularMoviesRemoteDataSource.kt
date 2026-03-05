package com.rocket.movielab.data.remote.datasource

import com.rocket.movielab.data.model.PaginatedData
import com.rocket.movielab.data.model.MovieResult

interface PopularMoviesRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Result<PaginatedData<MovieResult>>
}
