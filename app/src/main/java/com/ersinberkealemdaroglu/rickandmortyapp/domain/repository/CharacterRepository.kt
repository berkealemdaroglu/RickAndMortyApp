package com.ersinberkealemdaroglu.rickandmortyapp.domain.repository

import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import retrofit2.Response

interface CharacterRepository {

    suspend fun getCharacter(page : Int?) : Response<CharacterModel>
}