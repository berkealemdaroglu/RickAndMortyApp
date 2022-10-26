package com.ersinberkealemdaroglu.rickandmortyapp.data

import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterModel
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode.EpisodeItemResult
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode.EpisodeModel
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Constants.GET_CHARACTERS
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Constants.GET_EPISODE
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GET_CHARACTERS)
    suspend fun getCharacter(
        @Query("page") page : Int
    ) : CharacterModel

    @GET(GET_EPISODE)
    suspend fun getEpisode(
        @Query("page") page : Int
    ) : EpisodeModel
}