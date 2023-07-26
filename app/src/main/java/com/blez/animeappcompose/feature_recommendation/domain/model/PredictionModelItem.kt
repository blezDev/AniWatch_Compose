package com.blez.animeappcompose.feature_recommendation.domain.model

data class PredictionModelItem(
    val animeId: String,
    val animeImg: String,
    val animeTitle: String,
    val animeUrl: String,
    val status: String
)