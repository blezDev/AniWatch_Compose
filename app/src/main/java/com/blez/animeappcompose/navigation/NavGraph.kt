package com.blez.animeappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.blez.animeappcompose.HomeScreen
import com.blez.animeappcompose.feature_details.presentation.DetailViewModel
import com.blez.animeappcompose.feature_details.presentation.components.DetailContent
import com.blez.animeappcompose.feature_login.presentation.LoginScreen
import com.blez.animeappcompose.feature_popular.presentation.PopularViewModel
import com.blez.animeappcompose.feature_recent_release.presentation.RecentViewModel
import com.blez.animeappcompose.feature_recommendation.presentation.RecommendationViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: RecentViewModel,
    detailViewModel: DetailViewModel,
    popularViewModel: PopularViewModel,
    recommendationViewModel: RecommendationViewModel,
    startDestination: String
){
    NavHost(navController = navController, startDestination = startDestination ){
        composable(route = Screen.Home.route){
            HomeScreen(viewmodel = viewModel,navController = navController,popularViewModel,recommendationViewModel)
        }

        composable(route = Screen.Login.route){
            LoginScreen(navController)
        }

        composable(route = Screen.Detail.route,
        arguments = listOf(navArgument("animeId"){
            type = NavType.StringType
        })){
            DetailContent(navController,detailViewModel,animeId = it.arguments?.getString("animeId").toString())
        }
    }
}