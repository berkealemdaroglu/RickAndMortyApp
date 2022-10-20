package com.ersinberkealemdaroglu.rickandmortyapp.data

import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Constants.GET_CHARACTERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GET_CHARACTERS)
    suspend fun getCharacter(
        @Query("page") page: Int?
    ) : Response<CharacterModel>
}