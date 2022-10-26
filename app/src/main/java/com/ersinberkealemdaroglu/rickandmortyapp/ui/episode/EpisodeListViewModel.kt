package com.ersinberkealemdaroglu.rickandmortyapp.ui.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.EpisodeRepository
import com.ersinberkealemdaroglu.rickandmortyapp.ui.state.EpisodeItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(private val episodeRepository: EpisodeRepository) :
    ViewModel() {

    val episodeItemUiState = episodeRepository.getEpisode()
        .map { pagingData ->
            pagingData.map { episodeModel ->
                EpisodeItemUiState(episodeModel)
            }
        }.cachedIn(viewModelScope)

}