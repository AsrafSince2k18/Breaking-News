package com.example.pgtestnews.ui.presentance.component.shimmer

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pgtestnews.R


fun Modifier.shimmerEffect()=composed{
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue =0.2f,
        targetValue =0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha=alpha))
}

@Composable
fun ShimmerEffect(){

    Card(
        modifier = Modifier
            .padding(4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 2.dp
        )
    ) {
        Column(modifier = Modifier.padding(4.dp)) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .shimmerEffect())

            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .shimmerEffect())

            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .shimmerEffect())

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier
                    .width(50.dp)
                    .height(20.dp)
                    .shimmerEffect())
                Box(modifier = Modifier
                    .width(50.dp)
                    .height(20.dp)
                    .shimmerEffect())

            }
        }
    }


}

@Composable
@Preview
fun ShowPreview(){
    ShimmerEffect()
}