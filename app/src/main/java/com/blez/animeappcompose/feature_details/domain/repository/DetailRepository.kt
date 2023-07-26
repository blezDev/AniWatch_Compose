package com.blez.animeappcompose.feature_details.domain.repository

import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_details.domain.model.DetailModel

interface DetailRepository {
    suspend fun getAnimeDetail(animeId : String) : ResultState<DetailModel>
}