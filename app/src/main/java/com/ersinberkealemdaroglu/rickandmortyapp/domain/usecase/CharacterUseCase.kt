package com.ersinberkealemdaroglu.rickandmortyapp.domain.usecase

import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.CharacterRepository
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Resource
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend fun getCharacter(page : Int? = null): Resource<CharacterModel> {
        return try {
            val response = characterRepository.getCharacter(page)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No Data Catch", null)
        }
    }
}