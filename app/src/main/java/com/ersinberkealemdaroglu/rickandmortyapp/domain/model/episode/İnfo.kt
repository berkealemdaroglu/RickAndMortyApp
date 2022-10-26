package com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class İnfo(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String?
) : Parcelable
