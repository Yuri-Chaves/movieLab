package com.rocket.movielab.data.remote.datasource

import com.rocket.movielab.data.model.PaginatedData
import com.rocket.movielab.data.model.MovieResult
import com.rocket.movielab.data.remote.api.TheMovieDBApi

class PopularMoviesRemoteDataSourceImp: PopularMoviesRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): Result<PaginatedData<MovieResult>> {
        return TheMovieDBApi.getPopularMovies(page)
    }
}