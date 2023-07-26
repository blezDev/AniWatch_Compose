package com.blez.animeappcompose.feature_details.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.blez.animeappcompose.common.HistoryManager

@Composable
fun DetailScreen(viewModel: DetailViewModel,animeId : String) {
    viewModel.getAnimeDetail(animeId)
    val animeDetails = viewModel.animeDetail.value.animeDetails
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .clip(RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp))
                .shadow(10.dp), model = ImageRequest.Builder(LocalContext.current)
                .data(animeDetails.animeImg.toString())
                .crossfade(true)
                .build(),
            loading = { CircularProgressIndicator() },
            contentScale = ContentScale.FillBounds,
            contentDescription = "Anime Image"
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = animeDetails.animeTitle.toString(),
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier.fillMaxWidth(0.1f),text = animeDetails.releasedDate.toString())
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "|")
            Spacer(modifier = Modifier.width(5.dp))
            animeDetails.genres?.let {
                it.forEach {
                    Text(text = it)
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "|")
            Spacer(modifier = Modifier.width(5.dp))
            Text(modifier = Modifier.fillMaxWidth(0.1f),text = animeDetails.status.toString())
        }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Creating a boolean value for
            // storing expanded state
            var showMore by remember { mutableStateOf(false) }

            // Creating a long text
            val text = animeDetails.synopsis.toString()

            Column(modifier = Modifier.padding(20.dp)) {

                // Creating a clickable modifier
                // that consists text
                Column(modifier = Modifier
                    .animateContentSize(animationSpec = tween(100))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { showMore = !showMore }) {

                    // if showMore is true, the Text will expand
                    // Else Text will be restricted to 3 Lines of display
                    if (showMore) {
                        Text(text = text)
                    } else {
                        Text(text = text, maxLines = 3, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }


    }
}





