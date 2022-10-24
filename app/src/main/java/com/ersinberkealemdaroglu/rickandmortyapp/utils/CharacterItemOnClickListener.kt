package com.ersinberkealemdaroglu.rickandmortyapp.utils

import com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character.CharacterItem

interface CharacterItemOnClickListener {

    fun characterOnClickListener(characterItem: CharacterItem?)
}