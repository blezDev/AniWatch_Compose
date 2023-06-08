package com.blez.animeappcompose.feature_recent_release.domain.repository

import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem

interface RecentRepository {
    suspend fun getRecentRelease() : ResultState<List<RecentModelItem>>
}