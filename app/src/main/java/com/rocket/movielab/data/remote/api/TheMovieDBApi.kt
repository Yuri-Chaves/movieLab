package com.rocket.movielab.data.remote.api

import android.util.Log
import com.rocket.movielab.data.model.MovieDetailsResult
import com.rocket.movielab.data.model.PaginatedData
import com.rocket.movielab.data.model.MovieResult
import com.rocket.movielab.data.model.VideoResult
import com.rocket.movielab.data.remote.api.KtorHttpClient.httpClientAndroid
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

object TheMovieDBApi {
    const val BASE_URL = "https://api.themoviedb.org/3"

    private const val TRENDING_MOVIES_URL = "$BASE_URL/movie/popular?language=pt-BR"
    private const val SEARCH_MOVIES_URL = "$BASE_URL/search/movie?language=pt-BR&include_adult=true"

    private suspend inline fun<reified T> getFromApi(url: String): Result<T> = try {
        val response = httpClientAndroid.get(url)
        when (response.status) {
            HttpStatusCode.OK -> {
                val data = response.body<T>()
                Result.success(data)
            }
            HttpStatusCode.Unauthorized -> {
                Result.failure(Exception("Unauthorized"))
            }
            else -> {
                Log.e("getFromApi", "URL:$url\nHTTP ${response.status.value}", )
                Result.failure(Exception("Error"))
            }
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getPopularMovies(page: Int): Result<PaginatedData<MovieResult>> =
        getFromApi("$TRENDING_MOVIES_URL&page=$page")

    suspend fun searchMovie(query: String, page: Int): Result<PaginatedData<MovieResult>> =
        getFromApi("$SEARCH_MOVIES_URL&query=$query&page=$page")

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsResult> =
        getFromApi("$BASE_URL/movie/$movieId?language=pt-BR")

    suspend fun getMovieTrailers(movieId: Int): Result<VideoResult> =
        getFromApi("$BASE_URL/movie/$movieId/videos?language=pt-BR")
}