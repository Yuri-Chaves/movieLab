package com.rocket.movielab.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rocket.movielab.data.local.entity.PopularMovieEntity

@Dao
interface PopularMoviesDao {
    @Query("SELECT * FROM popular_movies ORDER BY page")
    fun getPopularMovies(): PagingSource<Int, PopularMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<PopularMovieEntity>)

    @Query("DELETE FROM popular_movies")
    suspend fun clearAll()
}
