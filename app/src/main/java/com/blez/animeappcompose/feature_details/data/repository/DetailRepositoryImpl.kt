package com.blez.animeappcompose.feature_details.data.repository

import android.content.Context
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.core.util.RunningCache
import com.blez.animeappcompose.core.util.checkForInternetConnection
import com.blez.animeappcompose.feature_details.data.remote.DetailsApi
import com.blez.animeappcompose.feature_details.domain.model.DetailModel
import com.blez.animeappcompose.feature_details.domain.repository.DetailRepository

class DetailRepositoryImpl(private val context: Context, private val detailsApi: DetailsApi) :
    DetailRepository {
    override suspend fun getAnimeDetail(animeId: String): ResultState<DetailModel> {
        try {
            if (!context.checkForInternetConnection()) {
                return ResultState.Error("No Internet Connection. Please Check your Connection!!")

            }
            if (RunningCache.animeDetails[animeId] != null) {
                return ResultState.Success(RunningCache.animeDetails[animeId])
            }

            val result = detailsApi.getAnimeDetails(animeId)
            RunningCache.animeDetails[animeId] = result.body()!!
            return ResultState.Success(data = result.body())
        } catch (e: Exception) {
            return ResultState.Error(e.message)
        }
    }
}