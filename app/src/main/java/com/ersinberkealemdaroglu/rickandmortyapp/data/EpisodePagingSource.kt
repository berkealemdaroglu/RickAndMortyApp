package com.ersinberkealemdaroglu.rickandmortyapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode.EpisodeItemResult
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Constants

class EpisodePagingSource(private val apiService: ApiService) :
    PagingSource<Int, EpisodeItemResult>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeItemResult>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeItemResult> {
        val page = params.key ?: Constants.STARTING_PAGE_INDEX
        return try {
            val response = apiService.getEpisode()
            LoadResult.Page(
                data = response.episodeItemResults,
                prevKey = if (page == Constants.STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.episodeItemResults.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}