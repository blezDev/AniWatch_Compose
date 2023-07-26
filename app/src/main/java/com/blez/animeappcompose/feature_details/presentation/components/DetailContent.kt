package com.blez.animeappcompose.feature_details.presentation.components

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.blez.animeappcompose.R
import com.blez.animeappcompose.common.HistoryManager
import com.blez.animeappcompose.feature_details.domain.model.DetailModel
import com.blez.animeappcompose.feature_details.presentation.DetailViewModel
import com.blez.animeappcompose.navigation.Screen
import com.blez.animeappcompose.ui.theme.EXPANDED_RADIUS_LEVEL
import com.blez.animeappcompose.ui.theme.EXTRA_LARGE_PADDING
import com.blez.animeappcompose.ui.theme.MIN_SHEET_HEIGHT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(navController: NavController, viewModel: DetailViewModel, animeId: String) {

    LaunchedEffect(key1 = true) {
        viewModel.getAnimeDetail(animeId)
    }
    var onBackPressed by remember {
        mutableStateOf(false)
    }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)
    )
    val animeDetails = viewModel.animeData.collectAsState(initial =DetailModel() )


    val currentSheetFraction = scaffoldState.currentSheetFraction
    Spacer(modifier = Modifier.height(5.dp))
    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            BottomSheetContents(
                animeDetails = animeDetails.value
            )
        },
        content = {
            BackgroundContent(
                animeImage = animeDetails.value .animeImg,
                imageFraction = currentSheetFraction
            ) {
                onBackPressed = true
            }
        }
    )


}

@OptIn(ExperimentalMaterial3Api::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = 1f
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == SheetValue.PartiallyExpanded && targetValue == SheetValue.PartiallyExpanded -> 1f
            currentValue == SheetValue.Expanded && targetValue == SheetValue.Expanded -> 0f
            currentValue == SheetValue.PartiallyExpanded && targetValue == SheetValue.Expanded -> 1f - fraction
            currentValue == SheetValue.Expanded && targetValue == SheetValue.PartiallyExpanded -> 0f + fraction
            else -> fraction
        }
    }


@Composable
fun BackgroundContent(
    animeImage: String? = "https://gogocdn.net/cover/kono-subarashii-sekai-ni-bakuen-wo.png",
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseClicked: () -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction + 0.2f)
                .shadow(10.dp), model = ImageRequest.Builder(LocalContext.current)
                .data(animeImage)
                .crossfade(true)
                .build(),
            loading = { CircularProgressIndicator() },
            contentScale = ContentScale.Crop,
            contentDescription = "Anime Image"
        )
    }
/*    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Button(
            onClick = { onCloseClicked },
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Back Button"
            )

        }

    }*/
}


@Composable
fun BottomSheetContents(
    infoBoxColor: Color = MaterialTheme.colorScheme.primary,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.outline,
    animeDetails: DetailModel
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.8f)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            Button(
                onClick = {}, colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .padding(5.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                    contentDescription = "Start Button"
                )
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
            Column {
                Text(
                    text = "${animeDetails.animeTitle}",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(all = 2.dp)
                )
                Row() {
                    Text(text = " ${animeDetails.releasedDate}", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "|", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    if ((animeDetails.genres?.size ?: 0) > 3) {
                        Text(
                            text = "${animeDetails.genres?.first()}, ${animeDetails?.genres?.get(1)}, ${
                                animeDetails.genres?.get(
                                    2
                                )
                            }", fontSize = 12.sp
                        )
                    } else {
                        Text(
                            text = "${animeDetails.genres}", fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "|", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "${animeDetails.status}", fontSize = 12.sp)
                }
                Column(
                    horizontalAlignment = Alignment.Start,

                    ) {

                    // Creating a boolean value for
                    // storing expanded state
                    var showMore by remember { mutableStateOf(false) }

                    // Creating a long text
                    val text =
                        "${animeDetails.synopsis}"

                    Column(modifier = Modifier.padding(5.dp)) {

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
                Text(
                    text = "${animeDetails.totalEpisodes} Episodes",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(5.dp)
                )
                LazyColumn(content = {
                    items(animeDetails.episodesList?.size ?: 0) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Episode ${animeDetails.episodesList?.get(it)?.episodeNum}",
                                fontSize = 16.sp
                            )
                            Image(
                                painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                                contentDescription = "Download Button"
                            )
                        }
                    }
                })
            }
        }
    }

}

