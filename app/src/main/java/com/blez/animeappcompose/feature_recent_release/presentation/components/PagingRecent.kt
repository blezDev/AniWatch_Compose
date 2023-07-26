package com.blez.animeappcompose.feature_recent_release.presentation.components

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.blez.animeappcompose.feature_recent_release.presentation.RecentViewModel

@Composable
fun PagingRecentScreen(viewModel: RecentViewModel){
    val recentPagingData = viewModel.recentPagingData.collectAsLazyPagingItems()

}