package com.ersinberkealemdaroglu.rickandmortyapp.domain.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val info: Info?,
    var results: List<CharacterItem>
) : Parcelable