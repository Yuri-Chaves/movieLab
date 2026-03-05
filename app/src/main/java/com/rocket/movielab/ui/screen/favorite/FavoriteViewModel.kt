package com.rocket.movielab.ui.screen.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rocket.movielab.domain.repository.FavoriteMoviesRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(
    val repository: FavoriteMoviesRepository
): ViewModel() {
    val favoriteMovies = repository.listFavoriteMovies().cachedIn(viewModelScope)

    fun onEvent(event: FavoriteEvent) {
        when(event) {
            is FavoriteEvent.RemoveFavorite -> removeFavorite(event.movieId)
        }
    }

    private fun removeFavorite(movieId: Int) {
        Log.d("removeFavorite", "Clicou")
        viewModelScope.launch {
            repository.removeFavorite(movieId)
        }
    }
}