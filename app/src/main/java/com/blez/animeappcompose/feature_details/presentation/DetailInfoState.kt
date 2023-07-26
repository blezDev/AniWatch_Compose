package com.blez.animeappcompose.feature_details.presentation

import com.blez.animeappcompose.feature_details.domain.model.DetailModel

data class DetailInfoState(
    val animeDetails : DetailModel = DetailModel(),
    val isLoading : Boolean = false
)
