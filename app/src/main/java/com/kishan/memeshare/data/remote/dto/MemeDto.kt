package com.kishan.memeshare.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.kishan.memeshare.domain.model.Meme
data class MemeDto(
    @SerializedName("author")
    val author: String,
    @SerializedName("nsfw")
    val nsfw: Boolean,
    @SerializedName("postLink")
    val postLink: String,
    @SerializedName("preview")
    val preview: List<String>,
    @SerializedName("spoiler")
    val spoiler: Boolean,
    @SerializedName("subreddit")
    val subreddit: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("ups")
    val ups: Int,
    @SerializedName("url")
    val url: String
)

fun MemeDto.toMeme():Meme{
    return Meme(
        title = title,
        url = url
    )
}