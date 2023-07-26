package com.blez.animeappcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blez.animeappcompose.common.CredentialManager
import com.blez.animeappcompose.feature_details.presentation.DetailViewModel
import com.blez.animeappcompose.feature_popular.presentation.PopularViewModel
import com.blez.animeappcompose.feature_popular.presentation.component.PopularScreen
import com.blez.animeappcompose.feature_recent_release.presentation.RecentViewModel
import com.blez.animeappcompose.feature_recent_release.presentation.components.RecentScreen
import com.blez.animeappcompose.feature_recommendation.presentation.RecommendationScreen
import com.blez.animeappcompose.feature_recommendation.presentation.RecommendationViewModel
import com.blez.animeappcompose.navigation.Screen
import com.blez.animeappcompose.navigation.SetupNavGraph
import com.blez.animeappcompose.ui.theme.AnimeAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeAppComposeTheme {
                val viewmodel: RecentViewModel = hiltViewModel()
                val navController = rememberNavController()
                val detailsViewModel: DetailViewModel = hiltViewModel()
                val popularViewModel: PopularViewModel = hiltViewModel()
                val recommendationViewModel : RecommendationViewModel = hiltViewModel()
                val context = LocalContext.current
                val loginManager = CredentialManager(context)
                SetupNavGraph(
                    navController = navController,
                    viewmodel,
                    detailsViewModel,
                    popularViewModel,
                    recommendationViewModel,
                    startDestination = if (loginManager.getName() != "User") Screen.Home.route else Screen.Login.route
                )
            }
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewmodel: RecentViewModel,
    navController: NavHostController,
    popularViewModel: PopularViewModel,
    recommendationViewModel: RecommendationViewModel
) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        val context = LocalContext.current
        val loginManager = CredentialManager(context)


        Row() {
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = "Hi, ${loginManager.getName().replace("@gmail.com","")}",
                fontSize = 22.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.ExtraBold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp), horizontalArrangement = Arrangement.End
            ) {
                Image(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(id = R.drawable.notification_blue),
                    contentDescription = "Notification Icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(id = R.drawable.person_male),
                    contentDescription = "Person Image"
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .clip(RoundedCornerShape(30)), value = "Search your anime", onValueChange = {})
        Spacer(modifier = Modifier.height(10.dp))
        //TrendingCard(modifier = Modifier, animeName = "Naruto", context = LocalContext.current)
        /*     Spacer(modifier = Modifier.height(5.dp))*/
        var gernes = listOf(
            "Action",
            "Adventure",
            "Comedy",
            "Drama",
            "Slice of Life",
            "Fantasy",
            "Magic",
            "Supernatural",
            "Horror",
            "Mystery",
            "Psychological",
            "Romance",
            "Sci-Fi",
            "Isekai"
        )
        /* Spacer(modifier = Modifier.height(50.dp))*/

        PopularScreen(navController, popularViewModel)
        Spacer(modifier = Modifier.height(50.dp))
        RecentScreen(navController, viewmodel)
        Spacer(modifier = Modifier.height(50.dp))
        RecommendationScreen(navController = navController,recommendationViewModel)
    }
}




