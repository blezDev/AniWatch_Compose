package com.blez.animeappcompose.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GenreText(text :String,modifier: Modifier = Modifier, onClick : (String)-> Unit){
    var state by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier
        .clickable { state = !state }
        .background(if (state) Color.Red else Color.Gray)
        .clip(RoundedCornerShape(50))){

        Text(text = text, color = Color.White, modifier = Modifier.wrapContentSize().padding(10.dp))
    }
}