package com.rocket.movielab.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.rocket.movielab.data.local.database.PopularMovieDatabase
import com.rocket.movielab.data.local.mediator.PopularMovieRemoteMediator
import com.rocket.movielab.data.mapper.toDomain
import com.rocket.movielab.data.remote.datasource.PopularMoviesRemoteDataSource
import com.rocket.movielab.domain.model.MovieCard
import com.rocket.movielab.domain.repository.PopularMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularMovieRepositoryImp(
    private val remoteDataSource: PopularMoviesRemoteDataSource,
    private val database: PopularMovieDatabase
) : PopularMovieRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovies(): Flow<PagingData<MovieCard>> {
        val pagingSourceFactory = {
            database.popularMoviesDao().getPopularMovies()
        }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = PopularMovieRemoteMediator(
                database = database,
                api = remoteDataSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }
    }
}