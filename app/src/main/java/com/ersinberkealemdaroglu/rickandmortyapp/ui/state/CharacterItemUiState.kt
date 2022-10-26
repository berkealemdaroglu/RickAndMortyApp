package com.ersinberkealemdaroglu.rickandmortyapp.ui.state

import com.ersinberkealemdaroglu.rickandmortyapp.common.BaseUiState
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode.EpisodeItemResult

data class CharacterItemUiState(
    private val characterItem: CharacterItem
    ) : BaseUiState() {

    fun getAllCharacter() = characterItem
}