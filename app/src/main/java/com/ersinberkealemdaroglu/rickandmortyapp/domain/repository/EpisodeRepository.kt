package com.ersinberkealemdaroglu.rickandmortyapp.domain.repository

import androidx.paging.PagingData
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode.EpisodeItemResult
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisode() : Flow<PagingData<EpisodeItemResult>>
}