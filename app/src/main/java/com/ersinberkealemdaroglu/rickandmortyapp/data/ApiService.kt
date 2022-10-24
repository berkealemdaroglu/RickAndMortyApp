package com.ersinberkealemdaroglu.rickandmortyapp.data

import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Constants.GET_CHARACTERS
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GET_CHARACTERS)
    suspend fun getCharacter(
        @Query("page") page: Int?,
    ) : CharacterModel
}