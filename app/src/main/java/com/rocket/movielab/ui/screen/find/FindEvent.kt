package com.rocket.movielab.ui.screen.find

interface FindEvent {
    data class OnSearchChange(val query: String) : FindEvent
    data object OnCleanSearch : FindEvent
}