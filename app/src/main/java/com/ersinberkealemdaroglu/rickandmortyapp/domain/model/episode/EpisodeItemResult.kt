package com.ersinberkealemdaroglu.rickandmortyapp.domain.model.episode

data class EpisodeItemResult(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)