package com.ersinberkealemdaroglu.rickandmortyapp.domain.repository

import androidx.paging.PagingData
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacter(): Flow<PagingData<CharacterItem>>
}