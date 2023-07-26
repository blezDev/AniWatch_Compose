package com.blez.animeappcompose.feature_recent_release.data.repository

import android.content.Context
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.blez.animeappcompose.core.util.ResultState
import com.blez.animeappcompose.core.util.RunningCache
import com.blez.animeappcompose.core.util.RunningCache.recentReleasesDetails
import com.blez.animeappcompose.core.util.checkForInternetConnection
import com.blez.animeappcompose.feature_recent_release.data.paging.RecentPagingSource
import com.blez.animeappcompose.feature_recent_release.data.remote.RecentAPI
import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem
import com.blez.animeappcompose.feature_recent_release.domain.repository.RecentRepository

class RecentRepositoryImpl(private val recentAPI: RecentAPI, private val context: Context) : RecentRepository {
    override suspend fun getRecentRelease(): ResultState<List<RecentModelItem>> {
      try {
          if (!context.checkForInternetConnection()) {
              return ResultState.Error("No Internet Connection. Please Check your Connection!!")

          }
          if (recentReleasesDetails[1] != null){
              return ResultState.Success(recentReleasesDetails[1])
          }

          val result = recentAPI.recentReleases()
          recentReleasesDetails[1] = result.body() ?: emptyList()
          return ResultState.Success(data = result.body())
      }catch (e : Exception){
          return ResultState.Error(e.message)
      }
    }


    override  fun getPagingRecentRelease(): Pager<Int, RecentModelItem> {

        return Pager(
            PagingConfig(pageSize = 20),
            pagingSourceFactory = { RecentPagingSource(recentAPI) }
        )
    }
}