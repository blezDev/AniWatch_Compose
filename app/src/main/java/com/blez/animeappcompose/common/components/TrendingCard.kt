package com.blez.animeappcompose.common.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun TrendingCard(
    modifier: Modifier = Modifier,
    animeName: String,
    imgUrl: String? = null,
    context: Context
) {
    Card(
        modifier = modifier.padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    modifier = modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    text = animeName
                )

                Row {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Star Icon")
                    Spacer(modifier = modifier.width(5.dp))
                    Text(text = "New Episode!")
                    Spacer(modifier = modifier.width(5.dp))
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Star Icon")
                }

            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 5.dp, bottom = 5.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                SubcomposeAsyncImage(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp)
                        .height(250.dp)
                        .clip(RoundedCornerShape(20)), model = ImageRequest.Builder(context)
                        .data(imgUrl ?: "https://gogocdn.net/images/anime/N/naruto.jpg")
                        .crossfade(true)
                        .build(),
                    loading = { CircularProgressIndicator() },
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Anime Image"
                )
            }
        }

    }

}