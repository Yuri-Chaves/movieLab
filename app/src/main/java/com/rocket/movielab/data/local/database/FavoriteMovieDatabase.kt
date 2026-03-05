package com.rocket.movielab.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rocket.movielab.data.local.dao.FavoriteMovieDao
import com.rocket.movielab.data.local.entity.FavoriteMovieEntity

const val FAVORITE_MOVIES_DATABASE = "favorite_movies_database"

@Database(
    entities = [
        FavoriteMovieEntity::class
    ],
    version = 1
)
abstract class FavoriteMovieDatabase: RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}