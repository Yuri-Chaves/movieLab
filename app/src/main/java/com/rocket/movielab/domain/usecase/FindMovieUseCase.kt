package com.rocket.movielab.domain.usecase

import androidx.paging.PagingData
import com.rocket.movielab.domain.model.MovieCard
import com.rocket.movielab.domain.repository.FindMovieRepository
import kotlinx.coroutines.flow.Flow

class FindMovieUseCase(
    private val repository: FindMovieRepository
) {
    fun searchMovie(query: String): Flow<PagingData<MovieCard>> =
        repository.searchMovies(query)
}