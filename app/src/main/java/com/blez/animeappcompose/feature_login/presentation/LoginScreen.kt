package com.blez.animeappcompose.feature_login.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.blez.animeappcompose.R
import com.blez.animeappcompose.common.CredentialManager
import com.blez.animeappcompose.navigation.Screen

@Composable
fun LoginScreen(navController: NavHostController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_anim))
    val compositionBottom by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_anime_bottom))
    var isPlaying by remember {
        mutableStateOf(true)
    }
    var isPlayingCenter by remember {
        mutableStateOf(false)
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying
    )

    val progressBottom by animateLottieCompositionAsState(
        composition = compositionBottom,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying
    )
    val compositionCenter by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_person))
    val progressCenter by animateLottieCompositionAsState(
        composition = compositionCenter,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlayingCenter
    )

    LaunchedEffect(key1 = progressCenter) {
        if (progressCenter == 0f) isPlayingCenter = true
        if (progressCenter == 1f) isPlayingCenter = false
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
        LottieAnimation(
            modifier = Modifier.size(200.dp),
            composition = composition,
            progress = progress
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
        LottieAnimation(
            modifier = Modifier.size(200.dp),
            composition = compositionBottom,
            progress = progressBottom
        )
    }
    var emailState by remember {
        mutableStateOf("Enter your email!!")
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                modifier = Modifier.size(200.dp),
                composition = compositionCenter,
                progress = progressCenter
            )
            OutlinedTextField(
                value = emailState,
                onValueChange = {
                    emailState = it
                }, shape = CircleShape
            )
            Spacer(modifier = Modifier.width(5.dp))
            val context = LocalContext.current
            ElevatedButton(onClick = {
                if (emailState.contains("@gmail.com")){
                    CredentialManager(context = context).saveName(emailState)
                    navController.navigate(Screen.Home.route)
                }else{
                    Toast.makeText(context, "Email need to be valid.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Login")

            }
        }


    }
}