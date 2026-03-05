package com.rocket.movielab.data.remote.datasource

import com.rocket.movielab.data.model.MovieDetailsResult
import com.rocket.movielab.data.model.MovieResult
import com.rocket.movielab.data.remote.api.TheMovieDBApi

class MovieDetailsRemoteDataSourceImp: MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsResult> =
        TheMovieDBApi.getMovieDetails(movieId)
}