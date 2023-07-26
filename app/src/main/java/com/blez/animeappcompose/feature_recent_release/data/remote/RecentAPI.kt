package com.blez.animeappcompose.feature_recent_release.data.remote

import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecentAPI {

    @GET("/recent-release")
    suspend fun recentReleases( @Query("page") page : Int = 1) : Response<List<RecentModelItem>>


}