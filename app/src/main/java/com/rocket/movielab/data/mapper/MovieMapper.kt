package com.rocket.movielab.data.mapper

import android.util.Log
import com.rocket.movielab.data.local.entity.FavoriteMovieEntity
import com.rocket.movielab.data.local.entity.PopularMovieEntity
import com.rocket.movielab.data.model.MovieDetailsResult
import com.rocket.movielab.data.model.MovieResult
import com.rocket.movielab.domain.extensions.getYear
import com.rocket.movielab.domain.extensions.toBrazilianDate
import com.rocket.movielab.domain.extensions.toDuration
import com.rocket.movielab.domain.model.MovieCard
import com.rocket.movielab.domain.model.MovieDetails

fun MovieResult.toEntity(page: Int, cachedAt: Long): PopularMovieEntity =
    PopularMovieEntity(
        id = this.id,
        popularity = this.popularity,
        adult = this.adult,
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
        page = page,
        cachedAt = cachedAt
    )

fun PopularMovieEntity.toDomain(): MovieCard =
    MovieCard(
        id = this.id,
        adult = this.adult,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
    )

fun MovieDetailsResult.toDomain(): MovieDetails =
    MovieDetails(
        adult = this.adult,
        backdropPath = this.backdropPath,
        posterPath = this.posterPath,
        genres = this.genres.map { it.name },
        id = this.id,
        overview = this.overview,
        releaseDate = this.releaseDate,
        runtime = this.runtime.toDuration(),
        title = this.title,
        voteAverage = this.voteAverage,
        popularity = this.popularity,
        isFavorite = false
    )

fun MovieResult.toCard(): MovieCard =
    MovieCard(
        id = this.id,
        adult = this.adult,
        title = this.title,
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        popularity = this.popularity
    )

fun FavoriteMovieEntity.toCard(): MovieCard =
    MovieCard(
        id = this.id,
        adult = this.adult,
        title = this.title,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        popularity = this.popularity
    )

fun MovieDetails.toFavoriteEntity(isFavorite: Boolean): FavoriteMovieEntity {
    Log.d("toFavoriteEntity", "MovieDetails\n$this")
    return FavoriteMovieEntity(
        id = this.id,
        adult = this.adult,
        title = this.title,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        popularity = this.popularity,
        genres = this.genres.joinToString("|"),
        overview = this.overview,
        isFavorite = isFavorite,
        backdropPath = this.backdropPath,
        runtime = this.runtime
    )
}