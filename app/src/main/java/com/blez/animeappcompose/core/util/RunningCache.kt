package com.blez.animeappcompose.core.util

import com.blez.animeappcompose.feature_recent_release.domain.model.RecentModelItem

object RunningCache {
    val recentReleasesDetails = mutableMapOf<Int, List<RecentModelItem>>()
}