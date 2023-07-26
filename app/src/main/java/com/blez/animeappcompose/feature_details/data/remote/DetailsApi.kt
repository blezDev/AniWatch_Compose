package com.blez.animeappcompose.feature_details.data.remote

import com.blez.animeappcompose.feature_details.domain.model.DetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {

    @GET("/anime-details/{animeId}")
    suspend fun getAnimeDetails(@Path("animeId") animeId: String): Response<DetailModel>
}