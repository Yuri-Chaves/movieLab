package com.rocket.movielab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rocket.movielab.data.model.Genres

@Entity(tableName = "popular_movies")
data class PopularMovieEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    val popularity: Double,
    val page: Int,
    val cachedAt: Long
)
