package com.blez.animeappcompose.feature_popular.data.repository

import android.content.Context
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.core.util.RunningCache
import com.blez.animeappcompose.core.util.checkForInternetConnection
import com.blez.animeappcompose.feature_popular.data.remote.PopularAPI
import com.blez.animeappcompose.feature_popular.domain.model.PopularModelItem
import com.blez.animeappcompose.feature_popular.domain.repository.PopRepository

class PopRepositoryImpl(val context : Context,val popularAPI: PopularAPI) : PopRepository {
    override suspend fun getPopularList(): ResultState<List<PopularModelItem>> {
      if(!context.checkForInternetConnection()){
          return ResultState.Error("Check your internet connectivity.")
      }
        if (RunningCache.popularAnimeDetails[1] != null){
            return ResultState.Success(RunningCache.popularAnimeDetails[1])
        }

        val result = popularAPI.getPopularAnime()
        RunningCache.popularAnimeDetails[1] = result.body() ?: emptyList()
        return ResultState.Success(data = result.body())
    }
}