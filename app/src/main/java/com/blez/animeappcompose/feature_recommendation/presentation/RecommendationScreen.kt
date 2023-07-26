package com.blez.animeappcompose.feature_recommendation.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blez.animeappcompose.R
import com.blez.animeappcompose.common.HistoryManager
import com.blez.animeappcompose.common.components.AnimeCard
import com.blez.animeappcompose.feature_popular.presentation.PopularViewModel
import com.blez.animeappcompose.navigation.Screen

@Composable
fun RecommendationScreen(navController: NavController,recommendationViewModel :RecommendationViewModel ) {

    val data by recommendationViewModel.popularData.collectAsState()
    val context = LocalContext.current
    val historyManager = HistoryManager(context = context)
    recommendationViewModel.getRecommendation(historyManager.getPreviousAnime())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row() {
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                painter = painterResource(id = R.drawable.tv_icon),
                modifier = Modifier.size(24.dp),
                contentDescription = "TV Icon"
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Recommendation",
                fontStyle = FontStyle.Normal,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.width(10.dp))
        }

        LazyRow(content = {
            items(count = data.size) { index ->
                AnimeCard(
                    imgUrl = data[index].animeImg,
                    animeName = data[index].animeTitle,
                    episodeNo = data[index].status.substring(data[index].status.length -1 -4,data[index].status.length),
                    animeId =data[index].animeId ,
                    context = LocalContext.current
                ){
                    navController.navigate(Screen.Detail.passAnimeId(it))
                }
            }

        })
    }

}