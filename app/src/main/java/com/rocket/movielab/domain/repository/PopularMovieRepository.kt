package com.rocket.movielab.domain.repository

import androidx.paging.PagingData
import com.rocket.movielab.domain.model.MovieCard
import kotlinx.coroutines.flow.Flow

interface PopularMovieRepository {

    fun getPopularMovies(): Flow<PagingData<MovieCard>>

}
