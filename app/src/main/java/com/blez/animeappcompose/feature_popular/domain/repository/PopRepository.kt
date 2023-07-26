package com.blez.animeappcompose.feature_popular.domain.repository

import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_popular.domain.model.PopularModelItem

interface PopRepository {

    suspend fun getPopularList() : ResultState<List<PopularModelItem>>
}