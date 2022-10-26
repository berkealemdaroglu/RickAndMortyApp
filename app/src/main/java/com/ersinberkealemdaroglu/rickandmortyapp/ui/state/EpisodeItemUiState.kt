package com.ersinberkealemdaroglu.rickandmortyapp.ui.state

import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode.EpisodeItemResult

class EpisodeItemUiState(
    private val episodeItemResult: EpisodeItemResult
) {
    fun getAllEpisode() = episodeItemResult
}