package com.kishan.memeshare.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.kishan.memeshare.presentation.meme_list.MemeListScreen
import com.kishan.memeshare.presentation.theme.MemeShareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemeShareTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MemeListScreen()
                }
            }
        }
    }
}



