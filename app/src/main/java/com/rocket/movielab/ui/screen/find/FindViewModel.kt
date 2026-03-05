package com.rocket.movielab.ui.screen.find

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rocket.movielab.domain.usecase.FindMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FindViewModel(
    useCase: FindMovieUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(FindUiState())
    val uiState: StateFlow<FindUiState> = _uiState.asStateFlow()

    val hasSearched = uiState.map { it.query.length >= 3 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults = uiState
        .map { it.query }
        .debounce(500)
        .map { it.trim() }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.length < 3) {
                flowOf(PagingData.empty())
            } else {
                useCase.searchMovie(query)
            }
        }
        .cachedIn(viewModelScope)

    fun onEvent(event: FindEvent) {
        when (event) {
            is FindEvent.OnSearchChange -> onSearchChange(event.query)
            is FindEvent.OnCleanSearch -> onCleanSearch()
        }
    }

    private fun onSearchChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    private fun onCleanSearch() {
        _uiState.update { it.copy(query = "") }
    }
}