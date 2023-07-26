package com.blez.animeappcompose.feature_details.domain.model

import com.google.gson.annotations.SerializedName

data class DetailModel(
    val animeImg: String ?= null,
    val animeTitle: String?= null,
    val episodesList: List<Episodes>?= null,
    val genres: List<String>?= null,
    val otherNames: String?= null,
    val releasedDate: String?= null,
    val status: String?= null,
    val synopsis: String?= null,
    val totalEpisodes: String?= null,
    val type: String?= null
)