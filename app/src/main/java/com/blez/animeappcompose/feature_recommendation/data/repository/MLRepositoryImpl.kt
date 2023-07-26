package com.blez.animeappcompose.feature_recommendation.data.repository

import android.content.Context
import com.blez.animeappcompose.common.ext.checkForInternetConnection
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_recommendation.data.remote.ML_API
import com.blez.animeappcompose.feature_recommendation.domain.model.PredictionModelItem
import com.blez.animeappcompose.feature_recommendation.domain.model.QueryModel
import com.blez.animeappcompose.feature_recommendation.domain.repository.MLRepository

class MLRepositoryImpl(val context: Context, val mlApi: ML_API) : MLRepository {
    override suspend fun getRecommendation(animeName: String): ResultState<List<PredictionModelItem>> {
        try {
            if (!context.checkForInternetConnection()) {
                return ResultState.Error("No Internet Connection")
            }
            val result = mlApi.predict(QueryModel(animeName))
            if (result.code() == 500){
                return ResultState.Success(mlApi.predict(QueryModel("Bleach")).body())
            }
            return ResultState.Success(result.body())

        } catch (e: Exception) {
            return ResultState.Error(e.message.toString())
        }

    }
}