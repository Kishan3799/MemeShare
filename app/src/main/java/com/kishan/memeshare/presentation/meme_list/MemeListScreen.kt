package com.kishan.memeshare.presentation.meme_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kishan.memeshare.presentation.meme_list.components.MemeListItem
import com.kishan.memeshare.presentation.meme_list.components.ShimmerListItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemeListScreen(
    viewModel: MemeListViewModel = hiltViewModel(),
) {
    var shimmerIsLoading by remember {
        mutableStateOf(true)
    }
    val state = viewModel.state.value
    val stateRefreshState = rememberPullRefreshState(state.refreshing, onRefresh = {viewModel.refreshingMeme()})
    LaunchedEffect(key1 = shimmerIsLoading) {
        delay(3000)
        shimmerIsLoading = false
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
        .pullRefresh(stateRefreshState)){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            if(!state.refreshing) {
                items(state.memes) { memes ->
//                    MemeListItem(meme = memes)
                    ShimmerListItem(isLoading = shimmerIsLoading, contentAfterLoading = { MemeListItem(meme = memes) }, modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp))
                }
            }
        }
        PullRefreshIndicator(refreshing = state.refreshing, state = stateRefreshState, Modifier.align(
            Alignment.TopCenter))
        if (state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}