package com.rocket.movielab.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rocket.movielab.data.paging.FindMoviePagingSource
import com.rocket.movielab.data.remote.datasource.FindMovieRemoteDataSource
import com.rocket.movielab.domain.model.MovieCard
import com.rocket.movielab.domain.repository.FindMovieRepository
import kotlinx.coroutines.flow.Flow

class FindMovieRepositoryImp(
    private val findMovieRemoteDataSource: FindMovieRemoteDataSource
): FindMovieRepository {
    override fun searchMovies(query: String): Flow<PagingData<MovieCard>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE / 2,
                initialLoadSize = PAGE_SIZE,
                maxSize = PAGE_SIZE * 100
            ),
        ) {
            FindMoviePagingSource(findMovieRemoteDataSource, query)
        }.flow
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}