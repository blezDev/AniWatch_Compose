package com.blez.animeappcompose.feature_recommendation.domain.repository

import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_recommendation.domain.model.PredictionModelItem

interface MLRepository {
    suspend fun getRecommendation(animeName : String) : ResultState<List<PredictionModelItem>>
}