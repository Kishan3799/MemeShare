package com.kishan.memeshare.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.kishan.memeshare.domain.model.MemesList

data class MemeListDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("memes")
    val memes: List<MemeDto>
)
fun MemeListDto.toMemesList():MemesList {
    return MemesList(
        memes = memes.map { it.toMeme() }
    )
}