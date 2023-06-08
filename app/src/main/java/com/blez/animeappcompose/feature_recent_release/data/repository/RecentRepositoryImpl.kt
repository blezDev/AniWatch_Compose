package com.blez.animeappcompose.feature_recent_release.data.repository

import android.content.Context
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.core.util.RunningCache
import com.blez.animeappcompose.core.util.RunningCache.recentReleasesDetails
import com.blez.animeappcompose.core.util.checkForInternetConnection
import com.blez.animeappcompose.feature_recent_release.data.remote.RecentAPI
import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem
import com.blez.animeappcompose.feature_recent_release.domain.repository.RecentRepository

class RecentRepositoryImpl(private val recentAPI: RecentAPI, private val context: Context) : RecentRepository {
    override suspend fun getRecentRelease(): ResultState<List<RecentModelItem>> {
        if (!context.checkForInternetConnection()) {
            return ResultState.Error("No Internet Connection. Please Check your Connection!!")

        }
        if (recentReleasesDetails[1] != null){
            return ResultState.Success(recentReleasesDetails[1])
        }

        val result = recentAPI.recentReleases()
        recentReleasesDetails[1] = result.body() ?: emptyList()
        return ResultState.Success(data = result.body())


    }
}