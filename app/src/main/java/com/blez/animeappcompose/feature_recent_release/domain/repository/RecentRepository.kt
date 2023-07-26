package com.blez.animeappcompose.feature_recent_release.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem

interface RecentRepository {
    suspend fun getRecentRelease() : ResultState<List<RecentModelItem>>


     fun  getPagingRecentRelease() : Pager<Int,RecentModelItem>
}