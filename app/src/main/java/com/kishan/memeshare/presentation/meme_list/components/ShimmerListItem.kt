package com.kishan.memeshare.presentation.meme_list.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerListItem(
    isLoading : Boolean,
    contentAfterLoading: @Composable ()-> Unit,
    modifier: Modifier
) {
    if(isLoading) {
      Column(modifier = modifier) {
          Box(modifier = Modifier
              .fillMaxWidth()
              .height(20.dp)
              .clip(RectangleShape)
              .shimmerEffect()
              .align(alignment = CenterHorizontally)
          )
          Spacer(modifier = Modifier.width(16.dp))
          Column(modifier = Modifier.padding(top = 16.dp)) {
              Box(modifier = Modifier
                  .fillMaxWidth()
                  .height(300.dp)
                  .clip(RectangleShape)
                  .shimmerEffect()
                  .align(Alignment.CenterHorizontally))
          }
      }
    } else {
        contentAfterLoading()
    }
}

fun Modifier.shimmerEffect():Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFC3BFBF),
                Color(0xFFFFFFFF) ,
                Color(0xFFA0A0A0)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

@Preview
@Composable
fun ShimmerPreview() {
    ShimmerListItem(isLoading = true, contentAfterLoading = { /*TODO*/ }, modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp))
}