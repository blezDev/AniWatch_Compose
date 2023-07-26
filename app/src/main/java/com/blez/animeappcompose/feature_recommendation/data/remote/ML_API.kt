package com.blez.animeappcompose.feature_recommendation.data.remote

import com.blez.animeappcompose.feature_recommendation.domain.model.PredictionModelItem
import com.blez.animeappcompose.feature_recommendation.domain.model.QueryModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ML_API {
    @POST("/predict")
    suspend fun predict( @Body queryModel : QueryModel) : Response<List<PredictionModelItem>>
}