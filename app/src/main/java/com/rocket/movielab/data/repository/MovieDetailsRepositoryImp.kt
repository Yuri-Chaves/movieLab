package com.rocket.movielab.data.repository

import android.util.Log
import com.rocket.movielab.data.local.dao.FavoriteMovieDao
import com.rocket.movielab.data.mapper.toDomain
import com.rocket.movielab.data.model.Video
import com.rocket.movielab.data.remote.api.TheMovieDBApi
import com.rocket.movielab.domain.model.MovieDetails
import com.rocket.movielab.domain.repository.MovieDetailsRepository

class MovieDetailsRepositoryImp(
    private val dao: FavoriteMovieDao
): MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails = try {
        val response = TheMovieDBApi.getMovieDetails(movieId)
        val isFavorite = dao.getFavoriteMovieById(movieId) != null

        if (response.isSuccess) {
            response.getOrThrow().toDomain().copy(isFavorite = isFavorite)
        } else {
            throw response.exceptionOrNull() ?: Exception("Error")
        }
    } catch (e: Exception) {
        Log.e("getMovieDetails", "Error: ${e.message}", )
        throw e
    }

    override suspend fun getMovieTrailers(movieId: Int): List<Video> = try {
        val response = TheMovieDBApi.getMovieTrailers(movieId)

        if (response.isSuccess) {
            response.getOrThrow().results
        } else {
            throw response.exceptionOrNull() ?: Exception("Error")

        }
    } catch (e: Exception) {
        Log.e("getMovieTrailers", "Error: ${e.message}", )
        throw e
    }
}