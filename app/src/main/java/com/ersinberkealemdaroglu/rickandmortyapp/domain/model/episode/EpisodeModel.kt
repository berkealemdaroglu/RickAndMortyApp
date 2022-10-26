package com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeModel(
    val info: İnfo,
    val results: List<EpisodeItemResult>
) : Parcelable