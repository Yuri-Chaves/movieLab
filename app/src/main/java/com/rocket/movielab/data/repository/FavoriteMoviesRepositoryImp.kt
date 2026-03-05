package com.rocket.movielab.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.rocket.movielab.data.local.dao.FavoriteMovieDao
import com.rocket.movielab.data.local.entity.FavoriteMovieEntity
import com.rocket.movielab.data.mapper.toCard
import com.rocket.movielab.domain.model.MovieCard
import com.rocket.movielab.domain.repository.FavoriteMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMoviesRepositoryImp(
    private val dao: FavoriteMovieDao
): FavoriteMoviesRepository {
    override fun listFavoriteMovies(): Flow<PagingData<MovieCard>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dao.getAllFavoriteMovies() }
        ).flow.map { pagingData ->
            pagingData.map { it.toCard() }
        }
    }

    override suspend fun addFavoriteMovie(movie: FavoriteMovieEntity) {
        dao.insertFavoriteMovie(movie)
    }

    override suspend fun removeFavorite(movieId: Int) {
        dao.deleteFavoriteMovie(movieId)
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}