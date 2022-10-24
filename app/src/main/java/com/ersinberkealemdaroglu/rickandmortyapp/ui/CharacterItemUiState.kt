package com.ersinberkealemdaroglu.rickandmortyapp.ui

import com.ersinberkealemdaroglu.rickandmortyapp.common.BaseUiState
import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem

data class CharacterItemUiState(private val characterItem: CharacterItem) : BaseUiState() {

    fun getAllCharacter() = characterItem
    fun getCharacterImage() = characterItem.image
    fun getCharacterName() = characterItem.name
}