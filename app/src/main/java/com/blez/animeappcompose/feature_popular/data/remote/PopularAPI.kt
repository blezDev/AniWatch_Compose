package com.blez.animeappcompose.feature_popular.data.remote

import com.blez.animeappcompose.feature_popular.domain.model.PopularModelItem
import retrofit2.Response
import retrofit2.http.GET

interface PopularAPI {

    @GET("/popular")
    suspend fun getPopularAnime() : Response<List<PopularModelItem>>
}