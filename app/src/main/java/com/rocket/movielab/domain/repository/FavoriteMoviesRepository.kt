package com.rocket.movielab.domain.repository

import androidx.paging.PagingData
import com.rocket.movielab.data.local.entity.FavoriteMovieEntity
import com.rocket.movielab.domain.model.MovieCard
import kotlinx.coroutines.flow.Flow

interface FavoriteMoviesRepository {
    fun listFavoriteMovies(): Flow<PagingData<MovieCard>>

    suspend fun addFavoriteMovie(movie: FavoriteMovieEntity): Unit

    suspend fun removeFavorite(movieId: Int): Unit

}