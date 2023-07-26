package com.blez.animeappcompose.feature_recent_release.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.blez.animeappcompose.core.util.RunningCache
import com.blez.animeappcompose.feature_recent_release.data.remote.RecentAPI
import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem

class RecentPagingSource(private val recentAPI : RecentAPI) : PagingSource<Int, RecentModelItem>() {
    override fun getRefreshKey(state: PagingState<Int, RecentModelItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecentModelItem> {
        return try {
            val position = params.key ?: 1

            if (RunningCache.recentReleasesDetails[position]==null){
                val response = recentAPI.recentReleases(position).body()
                RunningCache.recentReleasesDetails[position] = response ?: emptyList()
                return LoadResult.Page(
                    data = RunningCache.recentReleasesDetails[position]!!,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = position + 1
                )
            }else{
                return LoadResult.Page(
                    data = RunningCache.recentReleasesDetails[position]!!,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = position + 1
                )
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}