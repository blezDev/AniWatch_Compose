package com.blez.animeappcompose.feature_recent_release.data.remote

import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem
import retrofit2.Response

interface RecentAPI {

    suspend fun recentReleases() : Response<List<RecentModelItem>>
}