package com.rocket.movielab.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rocket.movielab.data.local.dao.RemoteKeysDao
import com.rocket.movielab.data.local.dao.PopularMoviesDao
import com.rocket.movielab.data.local.dao.PopularMetadataDao
import com.rocket.movielab.data.local.entity.PopularMovieEntity
import com.rocket.movielab.data.local.entity.RemoteKeys
import com.rocket.movielab.data.local.entity.PopularMetadata

const val POPULAR_MOVIES_DATABASE = "popular_movies_database"

@Database(
    entities = [
        PopularMovieEntity::class,
        RemoteKeys::class,
        PopularMetadata::class],
    version = 2
)
abstract class PopularMovieDatabase: RoomDatabase() {
    abstract fun popularMoviesDao(): PopularMoviesDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun popularMetadataDao(): PopularMetadataDao
}