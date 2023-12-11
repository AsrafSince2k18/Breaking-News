package com.example.pgtestnews.ui.presentance.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pgtestnews.R

@Composable
fun ImageHolder(
    url:String
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .crossfade(300)
            .build(),
        contentDescription ="Image",
        placeholder = painterResource(id = R.drawable.place_holder),
        error = painterResource(id = R.drawable.place_holder),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(16/9f)
            .clip(shape = RoundedCornerShape(8.dp))
    )
}