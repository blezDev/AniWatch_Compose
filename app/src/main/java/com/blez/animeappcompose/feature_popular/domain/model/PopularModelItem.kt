package com.blez.animeappcompose.feature_popular.domain.model

data class PopularModelItem(
    val animeId: String,
    val animeImg: String,
    val animeTitle: String,
    val animeUrl: String,
    val releasedDate: String
)