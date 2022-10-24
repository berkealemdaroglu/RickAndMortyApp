package com.ersinberkealemdaroglu.rickandmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ersinberkealemdaroglu.rickandmortyapp.data.ApiService
import com.ersinberkealemdaroglu.rickandmortyapp.data.CharacterPagingSource
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.CharacterRepository
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

class CharacterRepositoryImp (private val apiService: ApiService) : CharacterRepository {
    override fun getCharacter(): Flow<PagingData<CharacterItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { CharacterPagingSource(apiService) }
        ).flow
    }


}