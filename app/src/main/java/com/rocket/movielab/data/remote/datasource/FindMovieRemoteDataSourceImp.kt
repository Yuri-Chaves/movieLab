package com.rocket.movielab.data.remote.datasource

import com.rocket.movielab.data.model.MovieResult
import com.rocket.movielab.data.model.PaginatedData
import com.rocket.movielab.data.remote.api.TheMovieDBApi

class FindMovieRemoteDataSourceImp: FindMovieRemoteDataSource {
    override suspend fun searchMovie(
        query: String,
        page: Int
    ): Result<PaginatedData<MovieResult>> =
        TheMovieDBApi.searchMovie(query, page)
}