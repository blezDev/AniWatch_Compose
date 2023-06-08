package com.blez.animeappcompose.common.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.blez.animeappcompose.R

@Composable
fun AnimeCard(
    modifier: Modifier = Modifier,
    navigateToDetailScreen: (String?) -> Unit,
    context: Context
) {
    Column(modifier = modifier.fillMaxSize().background(Color.Black), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        SubcomposeAsyncImage(modifier = modifier
            .wrapContentSize()
            .align(CenterHorizontally)
            .clip(RoundedCornerShape(20)),model = ImageRequest.Builder(context)
            .data("https://gogocdn.net/images/anime/N/naruto.jpg")
            .crossfade(true)
            .build(),
            loading = {CircularProgressIndicator()},
            contentDescription = "Anime Image" )

        Text(modifier = modifier.fillMaxWidth(),text = "Naruto", textAlign = TextAlign.Center, color = Color.White)
        Text(modifier = modifier.fillMaxWidth(),text = "Episode 2", textAlign = TextAlign.Center,color = Color.White)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun animeCardPrev() {
    AnimeCard(navigateToDetailScreen = {}, context = LocalContext.current)
    
}