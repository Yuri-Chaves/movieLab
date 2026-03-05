package com.rocket.movielab.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rocket.movielab.data.local.entity.FavoriteMovieEntity

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovies(): PagingSource<Int, FavoriteMovieEntity>

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteFavoriteMovie(movieId: Int)

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getFavoriteMovieById(movieId: Int): FavoriteMovieEntity?
}
