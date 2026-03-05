package com.rocket.movielab.ui.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rocket.movielab.data.mapper.toFavoriteEntity
import com.rocket.movielab.domain.model.MovieDetails
import com.rocket.movielab.domain.repository.FavoriteMoviesRepository
import com.rocket.movielab.domain.usecase.MovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    val movieId: Int,
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val favoriteMoviesRepository: FavoriteMoviesRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    init {
        fetchMovieDetails(movieId)
    }

    private fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                movieDetailsUseCase.getMovieDetails(movieId)
            }.onSuccess { movie ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movie = movie,
                        error = null,
                        isFavorite = movie.isFavorite
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
        }
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.OnToggleFavorite -> toggleFavorite(event.movie)
        }
    }

    private fun toggleFavorite(movie: MovieDetails) {
        val isFavorite = !uiState.value.isFavorite
        if (isFavorite) {
            viewModelScope.launch {
                favoriteMoviesRepository.addFavoriteMovie(movie.toFavoriteEntity(true))
            }
        } else {
            viewModelScope.launch {
                favoriteMoviesRepository.removeFavorite(movie.id)
            }
        }
        _uiState.update { it.copy(isFavorite = isFavorite) }
    }
}