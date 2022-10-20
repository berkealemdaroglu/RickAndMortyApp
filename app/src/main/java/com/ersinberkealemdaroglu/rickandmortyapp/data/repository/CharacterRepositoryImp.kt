package com.ersinberkealemdaroglu.rickandmortyapp.data.repository

import com.ersinberkealemdaroglu.rickandmortyapp.data.ApiService
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.CharacterRepository
import retrofit2.Response

class CharacterRepositoryImp (private val apiService: ApiService) : CharacterRepository {
    override suspend fun getCharacter(page : Int?): Response<CharacterModel> {
        return apiService.getCharacter(page)
    }

}