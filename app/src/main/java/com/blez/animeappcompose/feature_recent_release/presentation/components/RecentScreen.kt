package com.blez.animeappcompose.feature_recent_release.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blez.animeappcompose.R
import com.blez.animeappcompose.common.components.AnimeCard
import com.blez.animeappcompose.feature_recent_release.presentation.RecentViewModel
import com.blez.animeappcompose.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RecentScreen(navController: NavController,viewModel: RecentViewModel) {
    val state by viewModel.recentData.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row() {
            Spacer(modifier = Modifier.width(5.dp))
            Icon(painter = painterResource(id = R.drawable.tv_icon), modifier = Modifier.size(24.dp), contentDescription = "TV Icon")
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Recent Releases",
                fontStyle = FontStyle.Normal,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
      LazyRow(content = {
          items(state.size){index->
              AnimeCard(
                  imgUrl = state[index].animeImg,
                  animeName = state[index].animeTitle,
                  episodeNo = state[index].episodeNum,
                  animeId = state[index].animeId,
                  context = LocalContext.current
              ){
                  navController.navigate(Screen.Detail.passAnimeId(it))
              }
          }
      })

    }
}