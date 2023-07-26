package com.blez.animeappcompose.common.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun AnimeCard(
    modifier: Modifier = Modifier,
    imgUrl : String?,
    animeName :String,
    episodeNo : String,
    context: Context,
    animeId : String,
    onClicked: (String)-> Unit
) {
    Column(modifier = modifier
        .width(200.dp)
        .height(250.dp)
        .padding(2.dp)
        .clickable { onClicked(animeId) }
        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Box(modifier = modifier
            .fillMaxSize()
            .align(CenterHorizontally)){
            SubcomposeAsyncImage(modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20)),model = ImageRequest.Builder(context)
                .data(imgUrl ?: "https://gogocdn.net/images/anime/N/naruto.jpg")
                .crossfade(true)
                .build(),
                loading = {CircularProgressIndicator()},
                contentScale = ContentScale.FillBounds,
                contentDescription = "Anime Image")
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 300f
                    )
                )
                .padding(12.dp))
            Box(modifier = Modifier.fillMaxSize().padding(12.dp), contentAlignment = Alignment.BottomEnd ){
                Text(text = episodeNo, fontSize = 22.sp, color = Color.White)
            }
            Box(modifier = Modifier.fillMaxSize().padding(12.dp), contentAlignment = Alignment.BottomStart ){
                Text(modifier = modifier.fillMaxWidth(0.6f),text = animeName, color = Color.White, textAlign = TextAlign.Start, maxLines = 3)
            }
            
            
        }


    }
}

