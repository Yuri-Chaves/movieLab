package com.rocket.movielab.data.remote.datasource

import com.rocket.movielab.data.model.MovieResult
import com.rocket.movielab.data.model.PaginatedData

interface FindMovieRemoteDataSource {
    suspend fun searchMovie(query: String, page: Int): Result<PaginatedData<MovieResult>>
}
