package com.rocket.movielab.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val isFavorite: Boolean,
    val backdropPath: String,
    val overview: String,
    val runtime: String,
    val genres: String,
    val adult: Boolean,
    val popularity: Double
)
