package com.blez.animeappcompose.feature_recent_release.domain.model

data class RecentModelItem(
    val animeId: String,
    val animeImg: String,
    val animeTitle: String,
    val episodeId: String,
    val episodeNum: String,
    val episodeUrl: String,
    val subOrDub: String
)