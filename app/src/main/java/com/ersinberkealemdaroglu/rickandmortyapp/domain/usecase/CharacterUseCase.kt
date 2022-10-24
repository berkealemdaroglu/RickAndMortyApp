package com.ersinberkealemdaroglu.rickandmortyapp.domain.usecase

import androidx.paging.PagingData
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.CharacterRepository
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend fun getCharacter(): Flow<PagingData<CharacterItem>> {
        return characterRepository.getCharacter()
    }


}