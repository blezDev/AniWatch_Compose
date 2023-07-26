package com.blez.animeappcompose.navigation

sealed class Screen(val route : String) {
    object Home : Screen("home_screen")
    object Login : Screen("login_screen")
    object Detail : Screen("detail_screen/{animeId}"){
        fun passAnimeId(animeId: String) : String{
            return "detail_screen/${animeId}"
        }
    }
}