package com.kishan.memeshare.presentation.meme_list

import com.kishan.memeshare.domain.model.Meme
import com.kishan.memeshare.domain.model.MemesList

data class MemeListState(
    val isLoading:Boolean = false,
    val memes:List<Meme> = emptyList(),
    val error: String = ""
)
