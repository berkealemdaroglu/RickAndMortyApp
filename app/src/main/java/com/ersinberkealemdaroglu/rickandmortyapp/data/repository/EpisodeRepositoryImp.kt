package com.ersinberkealemdaroglu.rickandmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ersinberkealemdaroglu.rickandmortyapp.data.ApiService
import com.ersinberkealemdaroglu.rickandmortyapp.data.CharacterPagingSource
import com.ersinberkealemdaroglu.rickandmortyapp.data.EpisodePagingSource
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode.EpisodeItemResult
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.EpisodeRepository
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Constants
import kotlinx.coroutines.flow.Flow

class EpisodeRepositoryImp(private val apiService: ApiService) : EpisodeRepository {
    override fun getEpisode(): Flow<PagingData<EpisodeItemResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.EPISODE_PAGE_SIZE
            ),
            pagingSourceFactory = { EpisodePagingSource(apiService) }
        ).flow
    }

}