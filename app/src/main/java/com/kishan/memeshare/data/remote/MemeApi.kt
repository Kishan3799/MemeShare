package com.kishan.memeshare.data.remote


import com.kishan.memeshare.data.remote.dto.MemeDto
import com.kishan.memeshare.data.remote.dto.MemeListDto

import retrofit2.http.GET

interface MemeApi {
//    @GET("/gimme")
//    suspend fun getMeme(): MemeDto
    @GET("/gimme/50")
    suspend fun getMemes(): MemeListDto
}