package com.blez.animeappcompose.core.util

import com.blez.animeappcompose.feature_details.domain.model.DetailModel
import com.blez.animeappcompose.feature_popular.domain.model.PopularModelItem
import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem

object RunningCache {
    val recentReleasesDetails = mutableMapOf<Int, List<RecentModelItem>>()
    val popularAnimeDetails = mutableMapOf<Int, List<PopularModelItem>>()
    val animeDetails = mutableMapOf<String,DetailModel>()
}