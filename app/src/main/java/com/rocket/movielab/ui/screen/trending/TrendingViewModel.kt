package com.rocket.movielab.ui.screen.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rocket.movielab.domain.repository.PopularMovieRepository

class TrendingViewModel(
    repository: PopularMovieRepository
): ViewModel() {
    val movies = repository.getPopularMovies().cachedIn(viewModelScope)
}